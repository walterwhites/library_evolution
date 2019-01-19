package com.walterwhites.library.business.client;

import io.sentry.Sentry;
import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;

public final class SentryJClient {

    public static SentryClient sentry;

    public static SentryClient init() {
        Sentry.init();
        sentry = SentryClientFactory.sentryClient();
        SentryJClient myClass = new SentryJClient();
        return sentry;
    }

    public static void exempleException(String message) {
        throw new UnsupportedOperationException(message);
    }

    public static void recordBreadcrumb(String breadcrumb) {
        // Record a breadcrumb in the current context. By default the last 100 breadcrumbs are kept.
        Sentry.getContext().recordBreadcrumb(
                new BreadcrumbBuilder().setMessage(breadcrumb).build()
        );
    }

    public static void setUser(String email) {
        // Set the user in the current context.
        Sentry.getContext().setUser(
                new UserBuilder().setEmail(email).build()
        );
    }

    public static void sendSimpleEvent(String message) {
        Sentry.capture(message);
    }

    public static void addExtra(String key, String value) {
        // Add extra data to future events in this context.
        Sentry.getContext().addExtra(key, value);
    }

    public static void addTag(String key, String value) {
        // Add an additional tag to future events in this context.
        Sentry.getContext().addTag(key, value);
    }
}