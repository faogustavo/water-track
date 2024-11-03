package dev.valvassori.water.analytics

interface AnalyticsLogger {
    fun logPageView()

    fun logError(errorMessage: String)
}

class AnalyticsLoggerImpl(
    private val pageName: String,
) : AnalyticsLogger {
    override fun logPageView() {
        println("[AnalyticsLoggerImpl] Page view: $pageName")
    }

    override fun logError(errorMessage: String) {
        println("[AnalyticsLoggerImpl] Error: $errorMessage on page: $pageName")
    }
}
