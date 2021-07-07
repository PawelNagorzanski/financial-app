package com.example.pawel.expense.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.pawel.expense.service.MyUserDetailsService;
import com.example.pawel.expense.config.JwtTokenProvider;

public class JwtAuthenticationFilter extends OncePerRequestFilter { // public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
    public JwtTokenProvider tokenProvider; // private JwtTokenProvider tokenProvider;

    // JwtTokenProvider tokenProvider = new JwtTokenProvider();
    
    
    @Autowired
    public MyUserDetailsService myUserDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        // JwtTokenProvider tokenProvider = new JwtTokenProvider();
        // MyUserDetailsService myUserDetailsService = new MyUserDetailsService();
        
    	try {
    		logger.info("Sprawdzamy czy mamy jwt"); // problem z parametrem request
            String jwt = getJwtFromRequest(request);
            logger.info("Tutaj mamy jwt z doFilterInternal: " + jwt);
            
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) { //             if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Long userId = tokenProvider.getUserIdFromJWT(jwt);

                UserDetails userDetails = myUserDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
    	logger.info("Zrobione");
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
    	logger.info("Sprawdzamy czy mamy bearerToken ");
        String bearerToken = request.getHeader("Authorization");
        logger.info(bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;

    }
}
