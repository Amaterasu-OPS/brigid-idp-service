package com.brigid.idp.application.use_cases.health

import com.brigid.idp.application.api.UseCaseInterface
import org.springframework.stereotype.Component

@Component
class HealthCase: UseCaseInterface<Any, String, Any>(null) {
    override fun handler(body: Any): String {
        return "OK"
    }
}