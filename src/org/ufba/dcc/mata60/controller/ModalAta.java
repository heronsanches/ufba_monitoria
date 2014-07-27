package org.ufba.dcc.mata60.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class ModalAta extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
    Window pdfwindow;
	
	@Wire
	org.zkoss.zul.Iframe iframe_pdfwindow;
	
	private final String caminhoAta = "/home/heron/workspaces-eclipses/workspace_graduation/"
			+ "ufba_monitoria/arquivos/atas/";
	private final String tipoArquivoAta = "application/pdf";
	
	
	 
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	    
		//recebe parâmetro de ListarProjeto.mostrarAta()
		String nomePDF = (String) Executions.getCurrent().getArg().get("nomePDF");
		
		//ler arquivo do disco e o coloca num frame dentro do modal
		File f = new File(caminhoAta+nomePDF);
		
		if(f.exists() && !f.isDirectory()){
			
			byte[] buffer = new byte[ (int) f.length() ]; 
			FileInputStream fs = new FileInputStream(f);
			fs.read( buffer ); 
			fs.close();
	    	
			ByteArrayInputStream is = new ByteArrayInputStream(buffer);
	   		AMedia amedia =new AMedia(nomePDF, "pdf", tipoArquivoAta, is);
	   		iframe_pdfwindow.setContent(amedia);
	   		
		}else{

			Mensagem.arquivoNaoExiste();
			
		}
		
	}
	

}
