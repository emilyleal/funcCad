package br.edu.senaisp.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import br.edu.senaisp.model.Funcionario;
import br.edu.senaisp.util.Log;

public class FuncionarioDAO {

private String pathBancoDados = "C:\\TEMP\\Teste\\FuncionarioBanco.csv";
	
	public boolean gravarFuncionario(List<Funcionario> funcionarios) throws Exception  {
		
	
				PrintWriter pw;
				try {
					pw = new PrintWriter(pathBancoDados, Charset.forName("UTF-8"));

					for (Funcionario a : funcionarios) {
						pw.print(a.getId());
						pw.print(";" + a.getNome());
						pw.print(";" + a.getCpf());
						pw.println();
					}

					return true;
				} catch (Exception e) {
					Log.escrever("Erro ao tentar gravar Funcionarios: " + e.getMessage());
					throw new Exception("Erro ao tentar gravar Funcionarios: " 
							+ e.getMessage());
				}
			}
			
			public List<Funcionario> lerFuncionarios(){
				List<Funcionario> resposta = new ArrayList<Funcionario>();
				
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(pathBancoDados));

					String linha;
					
					Funcionario tmp;
					while ((linha = br.readLine()) != null) {

						String[] palavras = linha.split(";");
						tmp = new Funcionario();
						tmp.setId(Long.parseLong(palavras[0]));
						tmp.setNome(palavras[1]);
						tmp.setCpf(palavras[2]);
						
						resposta.add(tmp);
					}

				} catch (Exception e) {
					Log.escrever(e.getMessage());
				} finally {
					try {
						br.close();
					} catch (IOException e) {
						Log.escrever("Não conseguiu liberar o recurso. " + e.getMessage());	
					}
				}
				return resposta;
			}
			
		

}


