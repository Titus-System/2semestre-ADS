package com.titus_systems.idscan.gui;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class VerificaFormatoSuportado {
    
    public static boolean verificarValidade(String caminhoArquivo) {
        File arquivoImagem = new File(caminhoArquivo);

        if (isFormatoSuportado(arquivoImagem)) {
            System.out.println("O formato do arquivo é suportado pelo ImageIO.");
            return true;
        } else {
            System.out.println("O formato do arquivo não é suportado pelo ImageIO.");
            return false;
        }
    }

    // Método para verificar se o formato do arquivo é suportado pelo ImageIO
    public static boolean isFormatoSuportado(File arquivo) {
        // Lista de formatos de imagem suportados pelo ImageIO
        List<String> formatosSuportados = Arrays.asList(ImageIO.getReaderFormatNames());

        // Obtém a extensão do arquivo
        String extensaoArquivo = getExtensaoArquivo(arquivo.getName());

        // Verifica se a extensão está na lista de formatos suportados
        return formatosSuportados.contains(extensaoArquivo.toLowerCase());
    }

    // Método auxiliar para obter a extensão do arquivo
    private static String getExtensaoArquivo(String nomeArquivo) {
        int indicePonto = nomeArquivo.lastIndexOf('.');
        if (indicePonto > 0 && indicePonto < nomeArquivo.length() - 1) {
            return nomeArquivo.substring(indicePonto + 1);
        }
        return "";
    }
}
