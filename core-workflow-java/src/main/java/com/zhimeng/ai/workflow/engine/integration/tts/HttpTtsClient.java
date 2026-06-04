package com.zhimeng.ai.workflow.engine.integration.tts;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * HTTP-based TTS (Text-to-Speech) integration.
 * Uses standard REST API — any OpenAI-compatible TTS service can be plugged in
 * by changing the api.url and api.key configuration.
 */
@Slf4j
@Component
public class HttpTtsClient {

    @Value("${tts.api.url:}")
    private String ttsApiUrl;

    @Value("${tts.api.key:}")
    private String ttsApiKey;

    @Value("${tts.api.voice:default}")
    private String voice;

    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build();

    /**
     * Synthesize speech from text. Returns a map containing the audio URL or base64 data.
     *
     * @param text        The text to convert to speech
     * @param options     Extra TTS options (voice, speed, format, etc.)
     * @return Map with "audioUrl" or "audioData" key
     */
    public Map<String, Object> synthesize(String text, Map<String, Object> options) {
        if (ttsApiUrl == null || ttsApiUrl.isBlank()) {
            log.warn("TTS API URL not configured, returning empty result");
            return Map.of("audioUrl", "", "message", "TTS service not configured");
        }

        Map<String, Object> body = new HashMap<>();
        body.put("model", "tts-1");
        body.put("input", text);
        body.put("voice", options.getOrDefault("voice", voice));
        body.put("response_format", options.getOrDefault("format", "mp3"));
        if (options.containsKey("speed")) {
            body.put("speed", options.get("speed"));
        }

        String json = JSON.toJSONString(body);
        Request request = new Request.Builder()
                .url(ttsApiUrl)
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .header("Authorization", "Bearer " + ttsApiKey)
                .header("Content-Type", "application/json")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                byte[] audioBytes = response.body().bytes();
                Map<String, Object> result = new HashMap<>();
                result.put("audioData", audioBytes);
                result.put("format", options.getOrDefault("format", "mp3"));
                result.put("size", audioBytes.length);
                log.info("TTS synthesis complete: {} bytes", audioBytes.length);
                return result;
            } else {
                log.error("TTS API returned status: {}", response.code());
                return Map.of("error", "TTS API error: " + response.code());
            }
        } catch (IOException e) {
            log.error("TTS API call failed", e);
            return Map.of("error", "TTS call failed: " + e.getMessage());
        }
    }

    /**
     * Check if TTS service is configured and available.
     */
    public boolean isAvailable() {
        return ttsApiUrl != null && !ttsApiUrl.isBlank();
    }
}
