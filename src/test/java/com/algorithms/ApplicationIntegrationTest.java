package com.algorithms;

import com.algorithms.sorts.SortDetails;
import com.algorithms.util.SortRepresentation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTest {

	@LocalServerPort
	private int port;

	private SockJsClient sockJsClient;

	private WebSocketStompClient stompClient;

	private final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

	@Before
	public void setup() {
		List<Transport> transports = new ArrayList<>();
		transports.add(new WebSocketTransport(new StandardWebSocketClient()));
		this.sockJsClient = new SockJsClient(transports);

		this.stompClient = new WebSocketStompClient(sockJsClient);
		this.stompClient.setMessageConverter(new MappingJackson2MessageConverter());
	}

	@Test
	public void getGreeting() throws Exception {

		TestSessionHandler handler = new TestSessionHandler();

		this.stompClient.connect("ws://localhost:{port}/visual-alg", this.headers, handler, this.port);

		// give time to the application to compute the result
		TimeUnit.SECONDS.sleep(14);

		assertThat(handler.getListOfIntermediateResults().size(), is(6));
	}

	private class TestSessionHandler extends StompSessionHandlerAdapter {

		private final List<SortRepresentation> listOfIntermediateResults = new ArrayList<>();

		public List<SortRepresentation> getListOfIntermediateResults() {
			return listOfIntermediateResults;
		}

		@Override
		public void afterConnected(final StompSession session, StompHeaders connectedHeaders) {

			session.subscribe("/visualize/sorting", new StompFrameHandler() {

				@Override
				public Type getPayloadType(StompHeaders headers) {
					return SortRepresentation.class;
				}

				@Override
				public void handleFrame(StompHeaders headers, Object payload) {
					SortRepresentation sortedArray = (SortRepresentation) payload;

					listOfIntermediateResults.add(sortedArray);
				}
			});

			session.send("/app/sort", new SortDetails(new Integer[]{3, 1, 9, 4, 6, 5}, "BUBBLE_SORT"));
		}
	}
}