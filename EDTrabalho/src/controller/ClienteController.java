package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.Produto;
import model.TipoProduto;

public class ClienteController implements ActionListener {

	JTextField txtNome;
	JTextField txtCPF;
	JTextField txtCelular;
	JTextField txtCNPJ;
	JTextField txtTelefone;
	JTextField txtNomeFantasia;
	JTextField txtEmail;
	JTextField txtCEP;
	JTextField txtEndereco;
	JTextField txtComplemento;
	JTextField txtNumporta;

	public ClienteController() {
		super();
		readPF();
		readPJ();
	}

	public ClienteController(JTextField txtNome, JTextField txtCPF, JTextField txtCelular, JTextField txtCNPJ,
			JTextField txtTelefone, JTextField txtNomeFantasia, JTextField txtEmail, JTextField txtCEP,
			JTextField txtEndereco, JTextField txtComplemento, JTextField txtNumporta) {

		super();
		readPF();
		readPJ();
		this.txtNome = txtNome;
		this.txtCPF = txtCPF;
		this.txtCelular = txtCelular;
		this.txtCNPJ = txtCNPJ;
		this.txtTelefone = txtTelefone;
		this.txtNomeFantasia = txtNomeFantasia;
		this.txtEmail = txtEmail;
		this.txtCEP = txtCEP;
		this.txtEndereco = txtEndereco;
		this.txtComplemento = txtComplemento;
		this.txtNumporta = txtNumporta;
	}

	public List<PessoaFisica> customerPF = new LinkedList<PessoaFisica>();
	public List<PessoaJuridica> customerPJ = new LinkedList<PessoaJuridica>();

	public List<PessoaFisica> readPF() {
		customerPF.clear();
		try {
			BufferedReader fr = new BufferedReader(new FileReader("data/clientePF.csv"));

			String txt;

			try {
				while ((txt = fr.readLine()) != null) {
					String[] pt = txt.split(";");
					customerPF.add(new PessoaFisica(pt[0], pt[1], pt[2], pt[3], pt[4], pt[5], Integer.parseInt(pt[6])));
				}
				fr.close();
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerPF;
	}

	public List<PessoaJuridica> readPJ() {
		customerPJ.clear();
		try {
			BufferedReader fr = new BufferedReader(new FileReader("data/clientePJ.csv"));

			String txt;

			try {
				while ((txt = fr.readLine()) != null) {
					String[] pt = txt.split(";");
					customerPJ.add(new PessoaJuridica(pt[0], pt[1], pt[2], pt[3], pt[4], pt[5], pt[6],
							Integer.parseInt(pt[7])));
				}
				fr.close();
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerPJ;
	}

	public PessoaFisica findPF(String cod) {
		for (PessoaFisica ppf : customerPF) {
			if (ppf.cpf.equals(cod))
				return ppf;
		}
		return null;
	}

	public PessoaJuridica findPJ(String cod) {
		for (PessoaJuridica ppj : customerPJ) {
			if (ppj.cnpj.equals(cod))
				return ppj;
		}
		return null;
	}

	public void update(String cod, boolean tip) {

		String patch = "";
		PessoaFisica personPF = null;
		PessoaJuridica personPJ = null;

		if (tip) {
			patch = "data/clientePF.csv";
			personPF = new PessoaFisica(cod, txtNome.getText(), txtCelular.getText(), txtCEP.getText(),
					txtEndereco.getText(), txtComplemento.getText(), Integer.parseInt(txtNumporta.getText()));
			customerPF.clear();
		} else {
			patch = "data/clientePJ.csv";
			personPJ = new PessoaJuridica(cod, txtNomeFantasia.getText(), txtTelefone.getText(), txtEmail.getText(),
					txtCEP.getText(), txtEndereco.getText(), txtComplemento.getText(),
					Integer.parseInt(txtNumporta.getText()));
			customerPJ.clear();
		}
		String updateFile = "";

		try {
			BufferedReader fr = new BufferedReader(new FileReader(patch));

			String txt;

			try {
				while ((txt = fr.readLine()) != null) {
					String[] pts = txt.split(";");
					if (txt.equals(""))
						continue;

					if (!pts[0].equals(String.valueOf(cod))) {
						updateFile += txt + "\n";
					} else {
						if (tip)
							updateFile += personPF.cpf + ";" + personPF.nome + ";" + personPF.celular + ";"
									+ personPF.cep + ";" + personPF.endereco + ";" + personPF.complemento + ";"
									+ Integer.toString(personPF.numPorta) + "\n";
						else
							updateFile += personPJ.cnpj + ";" + personPJ.nomeFantasia + ";" + personPJ.telefone + ";"
									+ personPJ.email + ";" + personPJ.cep + ";" + personPJ.endereco + ";"
									+ personPJ.complemento + ";" + Integer.toString(personPJ.numPorta) + "\n";
					}
				}

				fr.close();

				FileWriter fw = new FileWriter(patch);
				fw.append(updateFile);
				fw.close();

				if (tip) {
					readPF();
					JOptionPane.showMessageDialog(null, "Pessoa Física atualizada com sucesso!", "Update Cliente",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					readPJ();
					JOptionPane.showMessageDialog(null, "Pessoa Jurídica atualizada com sucesso!", "Update Cliente",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Cadastro Cliente", JOptionPane.ERROR_MESSAGE);
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Cadastro Cliente", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void remove(String cod, boolean tip) {			
		String patch = "";

		if (tip) {
			patch = "data/clientePF.csv";
			customerPF.clear();
		} else {
			patch = "data/clientePJ.csv";
			customerPJ.clear();
		}
		String updateFile = "";

		try {
			BufferedReader fr = new BufferedReader(new FileReader(patch));

			String txt;

			try {
				while ((txt = fr.readLine()) != null) {
					String[] pts = txt.split(";");
					if (txt.equals(""))
						continue;

					if (!pts[0].equals(String.valueOf(cod))) {
						updateFile += txt + "\n";
					} 
				}

				fr.close();

				FileWriter fw = new FileWriter(patch);
				fw.append(updateFile);
				fw.close();

				if (tip) {
					readPF();
					JOptionPane.showMessageDialog(null, "Pessoa Física removida com sucesso!", "Delete Cliente",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					readPJ();
					JOptionPane.showMessageDialog(null, "Pessoa Jurídica removida com sucesso!", "Delete Cliente",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Cadastro Cliente", JOptionPane.ERROR_MESSAGE);
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Cadastro Cliente", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("Adicionar")) {
			if (txtNome.getText().isEmpty() && txtCPF.getText().isEmpty() && txtCelular.getText().isEmpty()
					&& txtCNPJ.getText().isEmpty() && txtTelefone.getText().isEmpty() && txtNomeFantasia.getText().isEmpty()
					&& txtEmail.getText().isEmpty() && txtCEP.getText().isEmpty() && txtEndereco.getText().isEmpty()
					&& txtComplemento.getText().isEmpty() && txtNumporta.getText().isEmpty()) {
				return;
			}

			try {
				if (!txtCPF.getText().isEmpty()) {
					FileWriter fw = new FileWriter("data/clientePF.csv");

					PessoaFisica person = new PessoaFisica(txtCPF.getText(), txtNome.getText(), txtCelular.getText(),
							txtCEP.getText(), txtEndereco.getText(), txtComplemento.getText(),
							Integer.parseInt(txtNumporta.getText()));

					customerPF.add(person);
					for (PessoaFisica p : customerPF) {
						fw.append(p.cpf + ";" + p.nome + ";" + p.celular + ";" + p.cep + ";" + p.endereco + ";"
								+ p.complemento + ";" + Integer.toString(p.numPorta) + "\n");
					}
					JOptionPane.showMessageDialog(null, "Pessoa Física adicionada com sucesso!", "Cadastro Cliente",
							JOptionPane.INFORMATION_MESSAGE);
					fw.close();
				} else {
					FileWriter fw = new FileWriter("data/clientePJ.csv");

					PessoaJuridica person = new PessoaJuridica(txtCNPJ.getText(), txtNomeFantasia.getText(),
							txtTelefone.getText(), txtEmail.getText(), txtCEP.getText(), txtEndereco.getText(),
							txtComplemento.getText(), Integer.parseInt(txtNumporta.getText()));

					customerPJ.add(person);
					for (PessoaJuridica p : customerPJ) {
						fw.append(p.cnpj + ";" + p.nomeFantasia + ";" + p.telefone + ";" + p.email + ";" + p.cep + ";"
								+ p.endereco + ";" + p.complemento + ";" + Integer.toString(p.numPorta) + "\n");
					}
					JOptionPane.showMessageDialog(null, "Pessoa Jurídica adicionada com sucesso!", "Cadastro Cliente",
							JOptionPane.INFORMATION_MESSAGE);
					fw.close();
				}
				txtCPF.setText("");
				txtNome.setText("");
				txtCelular.setText("");
				txtCNPJ.setText("");
				txtNomeFantasia.setText("");
				txtTelefone.setText("");
				txtEmail.setText("");
				txtCEP.setText("");
				txtEndereco.setText("");
				txtComplemento.setText("");
				txtNumporta.setText("");
			} catch (IOException er) {
				JOptionPane.showMessageDialog(null, er.getMessage(), "Cadastro Cliente", JOptionPane.ERROR_MESSAGE);
			}

		} else if (cmd.equals("Limpar")) {
			txtCPF.setText("");
			txtNome.setText("");
			txtCelular.setText("");
			txtCNPJ.setText("");
			txtNomeFantasia.setText("");
			txtTelefone.setText("");
			txtEmail.setText("");
			txtCEP.setText("");
			txtEndereco.setText("");
			txtComplemento.setText("");
			txtNumporta.setText("");
		}

	}

}