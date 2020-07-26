package org.example.oauth2.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class InteractiveAuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        System.out.println("InteractiveAuthenticationSuccessEvent listener");
        System.out.println(event);
    }
}
