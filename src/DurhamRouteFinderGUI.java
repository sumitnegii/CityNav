import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.util.*;

public class DurhamRouteFinderGUI {
    public static void main(String[] args) {
        try {
            GraphProcessor graphProcessor = new GraphProcessor();
            FileInputStream fis = new FileInputStream("../data/durham.graph");
            graphProcessor.initialize(fis);

            // locations & coordinates
            Map<String, double[]> locationCoordinates = new HashMap<>();
            locationCoordinates.put("ISBT Crossing", new double[] { 35.994501, -78.885918 });
            locationCoordinates.put("Premnagar Bypass", new double[] { 36.037856, -78.978653 });
            locationCoordinates.put("Patel Nagar Junction", new double[] { 36.025675, -78.851109 });
            locationCoordinates.put("Ballupur Chowk", new double[] { 36.032095, -78.84141 });
            locationCoordinates.put("Rajpur Road North", new double[] { 35.980889, -78.857117 });
            locationCoordinates.put("Jakhan Crossing", new double[] { 36.005333, -78.86438 });
            locationCoordinates.put("EC Road T-Point", new double[] { 35.966232, -78.866129 });
            locationCoordinates.put("Kaulagarh Road Cross", new double[] { 36.011617, -78.955189 });
            locationCoordinates.put("Sahastradhara Cut", new double[] { 36.025501, -78.953762 });
            locationCoordinates.put("Survey Chowk", new double[] { 36.02859, -78.925309 });
            locationCoordinates.put("Ghantaghar East", new double[] { 36.020468, -78.905396 });
            locationCoordinates.put("Clock Tower", new double[] { 36.014498, -78.890634 });
            locationCoordinates.put("Paltan Bazaar Entrance", new double[] { 36.013942, -78.887329 });
            locationCoordinates.put("Dilaram Bazaar", new double[] { 35.991219, -78.862814 });
            locationCoordinates.put("Tehsil Chowk", new double[] { 36.013456, -78.87042 });
            locationCoordinates.put("GMS Road Crossing", new double[] { 36.017448, -78.889925 });
            locationCoordinates.put("Miyawala Junction", new double[] { 35.992678, -78.86909 });
            locationCoordinates.put("Ring Road Chowk", new double[] { 35.994891, -78.899034 });
            locationCoordinates.put("Prince Chowk", new double[] { 35.989709, -78.902124 });
            locationCoordinates.put("University Road", new double[] { 35.974926, -78.923432 });
            locationCoordinates.put("ONGC T-Point", new double[] { 36.000125, -78.908465 });
            locationCoordinates.put("Vasant Vihar Turn", new double[] { 36.006529, -78.922214 });
            locationCoordinates.put("Kanwali Road", new double[] { 36.010035, -78.92203 });
            locationCoordinates.put("Race Course Chowk", new double[] { 36.028659, -78.935781 });
            locationCoordinates.put("Clement Town Barrier", new double[] { 36.033475, -78.965306 });
            locationCoordinates.put("Doon School Gate", new double[] { 36.024529, -78.94681 });
            locationCoordinates.put("Rajendra Nagar", new double[] { 36.016615, -78.950897 });
            locationCoordinates.put("Doon Vihar Turn", new double[] { 35.998659, -78.958118 });
            locationCoordinates.put("Doiwala Road", new double[] { 35.972137, -78.943194 });
            locationCoordinates.put("Raipur Checkpost", new double[] { 35.983814, -78.957882 });
            locationCoordinates.put("Kargi Chowk", new double[] { 35.968169, -78.963053 });
            locationCoordinates.put("Dharampur Bazaar", new double[] { 36.020581, -78.949127 });
            locationCoordinates.put("Balliwala Turn", new double[] { 35.974351, -78.938334 });
            locationCoordinates.put("Selaqui Bypass", new double[] { 36.033395, -78.966004 });
            locationCoordinates.put("Indira Nagar Gate", new double[] { 36.010551, -78.928571 });
            locationCoordinates.put("IT Park Entrance", new double[] { 36.022429, -78.95359 });
            locationCoordinates.put("Banjarawala Main", new double[] { 36.015661, -78.936081 });

            String[] cityNames = locationCoordinates.keySet().stream().sorted().toArray(String[]::new);

            JFrame frame = new JFrame("🧭 Durham Route Finder");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(new EmptyBorder(20, 20, 20, 20));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel title = new JLabel("Durham Route Finder", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 18));
            title.setForeground(new Color(0x2E86C1));

            JComboBox<String> startLocationCombo = new JComboBox<>(cityNames);
            JTextField startLat = new JTextField(10);
            JTextField startLon = new JTextField(10);

            JComboBox<String> endLocationCombo = new JComboBox<>(cityNames);
            JTextField endLat = new JTextField(10);
            JTextField endLon = new JTextField(10);

            JButton submitButton = new JButton("🚗 Find Route");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(title, gbc);

            gbc.gridwidth = 1;
            gbc.gridy++;
            panel.add(new JLabel("Start Location:"), gbc);
            gbc.gridx = 1;
            panel.add(startLocationCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            panel.add(new JLabel("Latitude:"), gbc);
            gbc.gridx = 1;
            panel.add(startLat, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            panel.add(new JLabel("Longitude:"), gbc);
            gbc.gridx = 1;
            panel.add(startLon, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            panel.add(new JLabel("End Location:"), gbc);
            gbc.gridx = 1;
            panel.add(endLocationCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            panel.add(new JLabel("Latitude:"), gbc);
            gbc.gridx = 1;
            panel.add(endLat, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            panel.add(new JLabel("Longitude:"), gbc);
            gbc.gridx = 1;
            panel.add(endLon, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 2;
            panel.add(submitButton, gbc);

            startLocationCombo.addActionListener(e -> {
                double[] coords = locationCoordinates.get(startLocationCombo.getSelectedItem());
                startLat.setText(String.valueOf(coords[0]));
                startLon.setText(String.valueOf(coords[1]));
            });

            endLocationCombo.addActionListener(e -> {
                double[] coords = locationCoordinates.get(endLocationCombo.getSelectedItem());
                endLat.setText(String.valueOf(coords[0]));
                endLon.setText(String.valueOf(coords[1]));
            });

            submitButton.addActionListener(e -> {
                try {
                    double[] startCoords = {
                            Double.parseDouble(startLat.getText()),
                            Double.parseDouble(startLon.getText())
                    };
                    double[] endCoords = {
                            Double.parseDouble(endLat.getText()),
                            Double.parseDouble(endLon.getText())
                    };

                    Point p1 = new Point(startCoords[0], startCoords[1]);
                    Point p2 = new Point(endCoords[0], endCoords[1]);

                    Point nearestStart = graphProcessor.nearestPoint(p1);
                    Point nearestEnd = graphProcessor.nearestPoint(p2);

                    List route = graphProcessor.route(nearestStart, nearestEnd);
                    double distance = graphProcessor.routeDistance(route);

                    String visPath = "../data/durham.vis";
                    String imgPath = "../images/durham.png";
                    Visualize visualize = new Visualize(visPath, imgPath);
                    visualize.drawRoute(route);

                    JOptionPane.showMessageDialog(frame,
                            "Route distance: " + distance + " miles\n\n" +
                                    "Start Point: " + nearestStart + "\n" +
                                    "End Point: " + nearestEnd);

                } catch (InvalidAlgorithmParameterException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid parameters.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Unexpected error occurred.");
                    ex.printStackTrace();
                }
            });

            // first two entries
            startLocationCombo.setSelectedIndex(0);
            endLocationCombo.setSelectedIndex(1);

            frame.add(panel);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Initialization failed.");
        }
    }
}
