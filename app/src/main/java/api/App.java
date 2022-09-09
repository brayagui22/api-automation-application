package api;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        new CommService().main();
        new JwtService().main();
        new CampaignService().main();
        new PricingService().main();
        new WalletService().main();
        new RegistrationService().main();

    }
}
