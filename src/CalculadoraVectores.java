import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraVectores extends JFrame {

    private final JTextField txtX1 = new JTextField("1");
    private final JTextField txtY1 = new JTextField("2");
    private final JTextField txtX2 = new JTextField("3");
    private final JTextField txtY2 = new JTextField("4");
    private final JTextField txtEscalar = new JTextField("2");

    private final JLabel lblResultado = new JLabel("Resultado: ");

    public CalculadoraVectores() {
        super("Calculadora de Vectores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(500, 350);
        setLocationRelativeTo(null);

        JPanel panelCentro = new JPanel(new GridLayout(5, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCentro.add(new JLabel("Vector 1 X:"));
        panelCentro.add(txtX1);
        panelCentro.add(new JLabel("Vector 1 Y:"));
        panelCentro.add(txtY1);

        panelCentro.add(new JLabel("Vector 2 X:"));
        panelCentro.add(txtX2);
        panelCentro.add(new JLabel("Vector 2 Y:"));
        panelCentro.add(txtY2);

        panelCentro.add(new JLabel("Escalar:"));
        panelCentro.add(txtEscalar);

        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton btnSuma = new JButton("Sumar");
        JButton btnResta = new JButton("Restar");
        JButton btnEscalar = new JButton("× Escalar");
        JButton btnMagnitud = new JButton("Magnitud");

        panelBotones.add(btnSuma);
        panelBotones.add(btnResta);
        panelBotones.add(btnEscalar);
        panelBotones.add(btnMagnitud);

        JPanel panelResultado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));
        panelResultado.add(lblResultado);

        add(panelCentro, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        add(panelResultado, BorderLayout.NORTH);

        btnSuma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[] v1 = leerVector(txtX1, txtY1);
                double[] v2 = leerVector(txtX2, txtY2);
                double[] resultado = sumar(v1, v2);
                lblResultado.setText("Resultado: (" + redondear(resultado[0]) + ", " + redondear(resultado[1]) + ")");
            }
        });

        btnResta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[] v1 = leerVector(txtX1, txtY1);
                double[] v2 = leerVector(txtX2, txtY2);
                double[] resultado = restar(v1, v2);
                lblResultado.setText("Resultado: (" + redondear(resultado[0]) + ", " + redondear(resultado[1]) + ")");
            }
        });

        btnEscalar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[] v1 = leerVector(txtX1, txtY1);
                double escalar = leerEscalar();
                double[] resultado = multiplicarPorEscalar(v1, escalar);
                lblResultado.setText("Resultado: (" + redondear(resultado[0]) + ", " + redondear(resultado[1]) + ")");
            }
        });

        btnMagnitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[] v1 = leerVector(txtX1, txtY1);
                double resultado = magnitud(v1);
                lblResultado.setText("Magnitud: " + redondear(resultado));
            }
        });
    }

    private double[] leerVector(JTextField xField, JTextField yField) {
        double x = Double.parseDouble(xField.getText());
        double y = Double.parseDouble(yField.getText());
        return new double[]{x, y};
    }

    private double leerEscalar() {
        return Double.parseDouble(txtEscalar.getText());
    }

    private double[] sumar(double[] v1, double[] v2) {
        return new double[]{
            v1[0] + v2[0],
            v1[1] + v2[1]
        };
    }

    private double[] restar(double[] v1, double[] v2) {
        return new double[]{
            v1[0] - v2[0],
            v1[1] - v2[1]
        };
    }

    private double[] multiplicarPorEscalar(double[] v, double escalar) {
        return new double[]{
            v[0] * escalar,
            v[1] * escalar
        };
    }

    private double magnitud(double[] v) {
        return Math.sqrt(Math.pow(v[0], 2) + Math.pow(v[1], 2));
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraVectores ventana = new CalculadoraVectores();
            ventana.setVisible(true);
        });
    }
}
