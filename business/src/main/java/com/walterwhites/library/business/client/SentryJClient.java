package com.walterwhites.library.business.client;

import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;

public final class SentryJClient {

    public static SentryJClient sentry;

    public static SentryJClient init() {
        if (sentry == null) {
            Sentry.init();
            sentry = new SentryJClient();
        }
        return sentry;
    }

    public void exempleException(String message) {
        throw new UnsupportedOperationException(message);
    }

    public void recordBreadcrumb(String breadcrumb) {
        // Record a breadcrumb in the current context. By default the last 100 breadcrumbs are kept.
        Sentry.getContext().recordBreadcrumb(
                new BreadcrumbBuilder().setMessage(breadcrumb).build()
        );
    }

    public void setUser(String email) {
        // Set the user in the current context.
        Sentry.getContext().setUser(
                new UserBuilder().setEmail(email).build()
        );
    }

    public void sendSimpleEvent(String message) {
        Sentry.capture(message);
    }

    public void addExtra(String key, String value) {
        // Add extra data to future events in this context.
        Sentry.getContext().addExtra(key, value);
    }

    public void addTag(String key, String value) {
        // Add an additional tag to future events in this context.
        Sentry.getContext().addTag(key, value);
    }
}