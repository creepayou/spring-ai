package com.spring_ai.test;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.mistralai.MistralAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/mistral-ai")
public class MistralController {

	private final MistralAiChatModel chatClient;

	public MistralController(MistralAiChatModel chatClient) {
		this.chatClient = chatClient;
	}

	@GetMapping("/test-temp")
	public String testTemp(
			@RequestParam Double temp) {
		var options = MistralAiChatOptions.builder()
				.withTemperature(temp)
				.build();
		ChatResponse response = chatClient.call(
				new Prompt("describe egg.", options));

		return response.getResult().getOutput().getContent();
	}

	@GetMapping("/ask")
	public String testMistralAi(
			@RequestParam(defaultValue = "Describe the belko experiment.") String prompt) {
		ChatResponse response = chatClient.call(
				new Prompt(prompt));

		return response.getResult().getOutput().getContent();
	}

	@GetMapping("/ask-patient")
	public String testMistralAiFunction(
			@RequestParam(defaultValue = "What's Patient P001's health like today? please give me a detailed description.") String prompt) {
		var options = MistralAiChatOptions.builder()
				.withFunction("retrievePatientHealthStatus")
				.build();

		ChatResponse response = chatClient.call(
				new Prompt(prompt, options));

		return response.getResult().getOutput().getContent();
	}

}