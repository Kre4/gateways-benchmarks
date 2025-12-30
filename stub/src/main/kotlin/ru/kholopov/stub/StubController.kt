package ru.kholopov.stub

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class StubController {
    data class JsonResponse(
        val id: Int = 42,
        val payload: String,
    )

    @GetMapping("/api/v1/json")
    fun json(@RequestParam(required = false) payloadSize: Int?): ResponseEntity<JsonResponse> {
        val size = (payloadSize ?: 1024).coerceIn(0, 100_000)
        val payload = "a".repeat(size)
        return ResponseEntity.ok(JsonResponse(payload = payload))
    }
}
