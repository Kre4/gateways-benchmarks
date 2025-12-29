package ru.kholopov.stub

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.toJavaDuration

@RestController
class StubController {

    data class JsonResponse(
        val id: Int = 42,
        val payload: String,
    )

    @GetMapping("/success")
    fun alwaysSuccess(): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/error")
    fun withErrorResponse(): ResponseEntity<Unit> {
        throw RuntimeException("Expectable error")
    }

    @GetMapping("/delay")
    fun withDelay(@RequestParam(required = false) delayMillis: Int?) {
        val delay = delayMillis ?: 1_000
        Thread.sleep(delay.milliseconds.toJavaDuration())
    }

    @GetMapping("/api/v1/json")
    fun json(@RequestParam(required = false) payloadSize: Int?): ResponseEntity<JsonResponse> {
        val size = (payloadSize ?: 1024).coerceIn(0, 100_000)
        val payload = "a".repeat(size)
        return ResponseEntity.ok(JsonResponse(payload = payload))
    }
}
