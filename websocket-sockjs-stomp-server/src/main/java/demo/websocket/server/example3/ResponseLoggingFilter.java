package demo.websocket.server.example3;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ResponseLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(request, response);
        if (!isAsyncStarted(request)) {
            logger.debug(createMessage(response));
        }
    }

    private String createMessage(@NonNull HttpServletResponse response) {
        final var msg = new StringBuilder();
        msg.append("RESPONSE !!! ");
        msg.append(response.getStatus());
        msg.append(response.isCommitted());
        msg.append(response.getHeaders(HttpHeaders.CONTENT_LENGTH));
        return msg.toString();
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }
}
