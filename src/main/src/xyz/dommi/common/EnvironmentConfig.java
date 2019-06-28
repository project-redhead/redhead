package xyz.dommi.common;

public class EnvironmentConfig {

    public static class DiscordConfig {
        private String clientId;
        private String clientSecret;
        private String redirectUrl;

        public DiscordConfig(String clientId, String clientSecret) {
            this(clientId, clientSecret, "http://localhost:8080/finishedlogin");
        }

        public DiscordConfig(String clientId, String clientSecret, String redirectUrl) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.redirectUrl = redirectUrl;
        }

        public String getClientId() {
            return clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public String getRedirectUrl() {
            // TODO: Add default value in here
            return redirectUrl;
        }
    }

    public static String getDatabaseConnectionString() {
        return System.getenv("DATABASE_CONNECTION_STRING");
    }

    public static DiscordConfig getDiscordConfig() {
        return new DiscordConfig(
                System.getenv("OAUTH_DISCORD_ID"),
                System.getenv("OAUTH_DISCORD_SECRET"),
                System.getenv("OAUTH_DISCORD_REDIRECT") != ""
                        ? System.getenv("OAUTH_DISCORD_REDIRECT") : "http://localhost:8080/finishedlogin");
    }

    public static String getSecretKey() {
        String key = System.getenv("SECRET_KEY");

        if (key == "" || key == null)
            return "mysuperdupersecretkey!123";
        else
            return key;
    }
}
