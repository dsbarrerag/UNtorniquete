package view;

import controller.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private JTextField t_salida;
    private JTextField t_entrada;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField p_min;
    private JTextField p_salida;
    private int rutina = 1;

    public ControlPanel() {
        JLabel lblNewLabel = new JLabel("Numero de Torniquetes");
        lblNewLabel.setFont(new Font("Trebuchet MS", 0, 13));
        this.t_salida = new JTextField();
        this.t_salida.setColumns(10);
        final JSpinner n_torniquetes = new JSpinner();
        n_torniquetes.setModel(new SpinnerNumberModel(1, 1, 8, 1));
        final JSpinner t_activos = new JSpinner();
        t_activos.setEnabled(false);
        this.t_entrada = new JTextField();
        this.t_entrada.setColumns(10);
        JRadioButton e_cong = new JRadioButton("Entrada Congestionada");
        e_cong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ControlPanel.this.rutina = 1;
                ControlPanel.this.p_min.setEnabled(false);
                ControlPanel.this.p_salida.setEnabled(false);
            }
        });
        e_cong.setMnemonic('1');
        e_cong.setSelected(true);
        this.buttonGroup.add(e_cong);
        e_cong.setFont(new Font("Trebuchet MS", 0, 13));
        JRadioButton s_cong = new JRadioButton("Salida Congestionada");
        s_cong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControlPanel.this.rutina = 2;
                ControlPanel.this.p_min.setEnabled(false);
                ControlPanel.this.p_salida.setEnabled(false);
            }
        });
        s_cong.setMnemonic('2');
        this.buttonGroup.add(s_cong);
        s_cong.setFont(new Font("Trebuchet MS", 0, 13));
        JRadioButton sys_cong = new JRadioButton("Entrada y Salida Congestionadas");
        sys_cong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControlPanel.this.rutina = 3;
                ControlPanel.this.p_min.setEnabled(false);
                ControlPanel.this.p_salida.setEnabled(false);
            }
        });
        sys_cong.setMnemonic('3');
        this.buttonGroup.add(sys_cong);
        sys_cong.setFont(new Font("Trebuchet MS", 0, 13));
        JRadioButton manual = new JRadioButton("Manual");
        manual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControlPanel.this.rutina = 4;
                ControlPanel.this.p_min.setEnabled(true);
                ControlPanel.this.p_salida.setEnabled(true);
            }
        });
        manual.setMnemonic('4');
        this.buttonGroup.add(manual);
        manual.setFont(new Font("Trebuchet MS", 0, 13));
        JLabel lblTorniquetesActivos = new JLabel("Torniquetes Activos");
        lblTorniquetesActivos.setFont(new Font("Trebuchet MS", 0, 13));
        JLabel lblTiempoEntrada = new JLabel("Tiempo Entrada");
        lblTiempoEntrada.setFont(new Font("Trebuchet MS", 0, 13));
        JLabel lblTiempoSalida = new JLabel("Tiempo Salida");
        lblTiempoSalida.setFont(new Font("Trebuchet MS", 0, 13));
        JLabel lblPPorMinuto = new JLabel("Personas por Minuto");
        lblPPorMinuto.setFont(new Font("Trebuchet MS", 0, 13));
        this.p_min = new JTextField();
        this.p_min.setColumns(10);
        this.p_min.setEnabled(false);
        JLabel lblPorcentajeEnSalida = new JLabel("Porcentaje de Salida");
        lblPorcentajeEnSalida.setFont(new Font("Trebuchet MS", 0, 13));
        this.p_salida = new JTextField();
        this.p_salida.setColumns(10);
        this.p_salida.setEnabled(false);
        final JButton btnGenerar = new JButton("Generar");
        btnGenerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (!ControlPanel.this.t_entrada.getText().isEmpty() && !ControlPanel.this.t_salida.getText().isEmpty()) {
                    if (ControlPanel.this.checkint(ControlPanel.this.t_entrada.getText()) && ControlPanel.this.checkint(ControlPanel.this.t_salida.getText())) {
                        int num = ((Integer)n_torniquetes.getValue()).intValue();
                        double tEnt = Double.parseDouble(ControlPanel.this.t_entrada.getText());
                        double tSal = Double.parseDouble(ControlPanel.this.t_salida.getText());
                        n_torniquetes.setEnabled(false);
                        t_activos.setModel(new SpinnerNumberModel(num, 1, num, 1));
                        t_activos.setEnabled(true);
                        if (ControlPanel.this.rutina == 4) {
                            if (!ControlPanel.this.p_min.getText().isEmpty() && !ControlPanel.this.p_salida.getText().isEmpty()) {
                                if (ControlPanel.this.checkint(ControlPanel.this.p_min.getText()) && ControlPanel.this.checkint(ControlPanel.this.p_salida.getText())) {
                                    int personas = Integer.parseInt(ControlPanel.this.p_min.getText());
                                    double porcentaje = Double.parseDouble(ControlPanel.this.p_salida.getText());
                                    App.getInstance().crear(num, tEnt, tSal, personas, porcentaje);
                                    btnGenerar.setEnabled(false);
                                } else {
                                    JOptionPane.showMessageDialog(btnGenerar, "Los campos deben ser numeros", "Alerta", 2);
                                }
                            } else {
                                JOptionPane.showMessageDialog(btnGenerar, "Los campos de generacion manuales no deben estar vacios", "Alerta", 2);
                            }
                        } else {
                            App.getInstance().crear(num, tEnt, tSal, ControlPanel.this.rutina);
                            btnGenerar.setEnabled(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(btnGenerar, "Los campos deben ser numeros", "Alerta", 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(btnGenerar, "Tiempo de entrada o salida vacios", "Alerta", 2);
                }

            }
        });
        btnGenerar.setFont(new Font("Trebuchet MS", 0, 13));
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setFont(new Font("Trebuchet MS", 0, 13));
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lblTorniquetesActivos, -2, 145, -2).addComponent(lblTiempoEntrada, -2, 145, -2).addComponent(lblTiempoSalida, -2, 145, -2).addComponent(lblPPorMinuto, -2, 156, -2).addComponent(lblPorcentajeEnSalida, -2, 156, -2)).addGap(18, 20, 32767).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.p_salida, -2, 52, -2).addComponent(this.p_min, -2, 52, -2).addComponent(this.t_salida, 0, 0, 32767).addComponent(this.t_entrada, 0, 0, 32767).addComponent(t_activos, -1, 52, 32767).addComponent(n_torniquetes, -1, 52, 32767)).addContainerGap(12, 32767)).addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(manual).addGap(188)).addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(lblNewLabel).addGap(112)).addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(e_cong).addGap(98)).addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(s_cong).addGap(102)).addGroup(groupLayout.createSequentialGroup().addGap(33).addComponent(btnGenerar).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(btnActualizar, -1, 106, 32767).addGap(35)).addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(sys_cong).addContainerGap(26, 32767)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblNewLabel).addComponent(n_torniquetes, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTorniquetesActivos, -2, 16, -2).addComponent(t_activos, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTiempoEntrada, -2, 16, -2).addComponent(this.t_entrada, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTiempoSalida, -2, 16, -2).addComponent(this.t_salida, -2, -1, -2)).addGap(3).addComponent(e_cong).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(s_cong).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(sys_cong).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(manual).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblPPorMinuto, -2, 16, -2).addComponent(this.p_min, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblPorcentajeEnSalida, -2, 16, -2).addComponent(this.p_salida, -2, -1, -2)).addGap(18).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnActualizar).addComponent(btnGenerar)).addContainerGap(17, 32767)));
        this.setLayout(groupLayout);
    }

    private boolean checkint(String text) {
        boolean rt = false;

        try {
            Double.parseDouble(text);
            rt = true;
        } catch (Exception var4) {
            rt = false;
        }

        return rt;
    }
}
