package io.ryos.app.web.filter;

import io.ryos.app.policy.RateLimiter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.springframework.web.servlet.HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;

public class RateLimiterInterceptor implements HandlerInterceptor {
    private static final int TOO_MANY_REQUEST = 429;
    private static final String USER_ID = "userId";

    private final RateLimiter rateLimiter;

    public RateLimiterInterceptor(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        Map<String, String> pathVars =
                (Map<String, String>)
                request.getAttribute(URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if (!rateLimiter.tryAccess(pathVars.get(USER_ID))) {
            response.setStatus(TOO_MANY_REQUEST);
            response.setHeader("Retry-after", "60");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
    }
}
