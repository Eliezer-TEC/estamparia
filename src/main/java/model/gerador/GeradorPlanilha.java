package model.gerador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.vo.Pessoa;

public class GeradorPlanilha {
	
	public String gerarPlanilhaClientes(ArrayList<Pessoa> pessoas, String destinoArquivo) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Clientes");
		
		HSSFRow linhaCabecalho = abaPlanilha.createRow(0);
		linhaCabecalho.createCell(0).setCellValue("Nome");
		linhaCabecalho.createCell(1).setCellValue("CPF");
		linhaCabecalho.createCell(2).setCellValue("Email");
		linhaCabecalho.createCell(4).setCellValue("Senha");
		linhaCabecalho.createCell(5).setCellValue("Funcionario?");
		
		int contadorLinhas = 1;
		for(Pessoa c: pessoas) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(c.getNome());
			novaLinha.createCell(1).setCellValue(c.getCpf());
			novaLinha.createCell(2).setCellValue(c.getEmail());
			novaLinha.createCell(3).setCellValue(c.getSenha());
			novaLinha.createCell(4).setCellValue(c.isFuncionario() ? "Sim" : "Não");
			contadorLinhas++;
		}
		
		return salvarNoDisco(arquivoExcel, destinoArquivo);
	}
	
	
	private String salvarNoDisco(HSSFWorkbook planilha, String caminhoArquivo) {
		String mensagem = "";
		FileOutputStream saida = null;
		String extensao = ".xls";

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha (sem acesso): " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro de I/O ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}
}
