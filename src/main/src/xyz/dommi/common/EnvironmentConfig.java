package xyz.dommi.common;

public class EnvironmentConfig {

    public static class DiscordConfig {
        private String clientId;
        private String clientSecret;

        public DiscordConfig(String clientId, String clientSecret) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
        }

        public String getClientId() {
            return clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }
    }

    public static String getDatabaseConnectionString() {
        return System.getenv("DATABASE_CONNECTION_STRING");
    }

    public static DiscordConfig getDiscordConfig() {
        return new DiscordConfig(
                System.getenv("OAUTH_DISCORD_ID"),
                System.getenv("OAUTH_DISCORD_SECRET")
        );
    }
}
