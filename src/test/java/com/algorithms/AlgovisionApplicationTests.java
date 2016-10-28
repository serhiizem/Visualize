package com.algorithms;

import com.algorithms.sorts.SortDetails;
import com.algorithms.util.SortRepresentation;
import org.hamcrest.Matchers;
import org.junit.Assert;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlgovisionApplicationTests {

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
	public void handlerTest() {

		TestHandler handler = new TestHandler("/visualize/sorting");
		this.stompClient.connect("ws://localhost:{port}/visual-alg", this.headers, handler, this.port);
		System.out.println(handler.getSortRepresentations().toString());
		Assert.assertThat(handler.getSortRepresentations(), not(empty()));
	}

	static class TestHandler extends StompSessionHandlerAdapter {

		private final String topic;

		private final List<SortRepresentation> sortRepresentations = new ArrayList<>();

		public TestHandler(String topic) {
			this.topic = topic;
		}

		public List<SortRepresentation> getSortRepresentations() {
			return sortRepresentations;
		}

		@Override
		public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
			session.subscribe(this.topic, new StompFrameHandler() {
				@Override
				public Type getPayloadType(StompHeaders headers) {
					return SortRepresentation.class;
				}

				@Override
				public void handleFrame(StompHeaders headers, Object payload) {
					sortRepresentations.add((SortRepresentation) payload);
				}
			});

			try {
				session.send("/app/sort", new SortDetails(new Integer[]{3, 1, 4, 7, 8, 5}, "BUBBLE_SORT"));
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}

		@Override
		public void handleFrame(StompHeaders headers, Object payload) {
			sortRepresentations.add((SortRepresentation) payload);
		}
	}
}
