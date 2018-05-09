package com.comedyapp.socket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.comedyapp.service.CommentsService;
import com.comedyapp.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class WebSocketController {

	private final SimpMessagingTemplate template;

	@Autowired
	CommentsService commentsService;

	@Autowired
	UserService userService;

	@Autowired
	WebSocketController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/send/message")
	public void onReceivedMessage(String message) {
		this.template.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date()) + " - " + message);
	}

	@MessageMapping("/{postId}")
	private void sendMessageTpPrivateRoom(String message, @DestinationVariable int postId) throws IOException {
		this.template.convertAndSend("/privateRoom/" + postId, message);
	}
}
