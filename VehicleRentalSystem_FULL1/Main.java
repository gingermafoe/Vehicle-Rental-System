
import javax.swing.UIManager;
public class Main {
    public static void main(String[] args) {
        // Set Nimbus Look & Feel if available
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        javax.swing.SwingUtilities.invokeLater(() -> {
            VehicleRentalSystem app = new VehicleRentalSystem();
            app.loadAll();
            app.buildDemoDataIfEmpty();
            app.buildUI();
        });
    }
}
