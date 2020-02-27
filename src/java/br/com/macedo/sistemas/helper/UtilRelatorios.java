
package br.com.macedo.sistemas.helper;

import br.com.macedo.sistemas.bean.LancamentoFacade;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Gustavo
 */
public class UtilRelatorios {
    @EJB
    private static LancamentoFacade lancamentoFacade;

    public LancamentoFacade getLancamentoFacade() {
        return lancamentoFacade;
    }
    
    
    
    public static void imprimeRelatorio(String relatorioNome, 
			HashMap parametros, List lista){
        
        System.out.println("lista"+lista);
		try {
			JRBeanCollectionDataSource dataSource = 
					new JRBeanCollectionDataSource(lista);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ServletContext scontext = 
					(ServletContext) facesContext.getExternalContext().getContext();
			String path = scontext.getRealPath("/WEB-INF/relatorios/");
			parametros.put("SUBREPORT_DIR", path + File.separator);
			JasperPrint jasperPrint = 
					JasperFillManager.fillReport(
					scontext.getRealPath("/WEB-INF/relatorios") +
					File.separator+ relatorioNome+".jasper",
					parametros, dataSource);
			byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpServletResponse res = (HttpServletResponse)
					facesContext.getExternalContext().getResponse();
			res.setContentType("application/pdf");
			int codigo = (int) (Math.random() * 1000);
			res.setHeader("Content-disposition", "inline);filename=relatorio_"+codigo+".pdf");
                        res.getOutputStream().write(b);
                        res.getOutputStream().close();
			facesContext.renderResponse();
		} catch(Exception e){
			UtilMensagens.mensagemErro("Erro ao imprimir: "+UtilErros.getMensagemErro(e));
			e.printStackTrace();
		}

	}

    public static void imprimeRelatorioPorMesa(String pedido,
            HashMap parametros, List pedidos) {
        try {
			JRBeanCollectionDataSource dataSource;
                        dataSource = new JRBeanCollectionDataSource(pedidos);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ServletContext scontext = 
					(ServletContext) facesContext.getExternalContext().getContext();
			String path = scontext.getRealPath("/WEB-INF/relatorios/");
			parametros.put("SUBREPORT_DIR", path + File.separator);
			JasperPrint jasperPrint = 
					JasperFillManager.fillReport(
					scontext.getRealPath("/WEB-INF/relatorios") +
					File.separator+ pedido+".jasper",
					parametros, dataSource);
			byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
			HttpServletResponse res = (HttpServletResponse)
					facesContext.getExternalContext().getResponse();
			res.setContentType("application/pdf");
			int codigo = (int) (Math.random() * 1000);
			res.setHeader("Content-disposition", "inline);filename=relatorio_"+codigo+".pdf");
                        res.getOutputStream().write(b);
                        res.getOutputStream().close();
			facesContext.renderResponse();
                        
                        
		} catch(Exception e){
			UtilMensagens.mensagemErro("Erro ao imprimir: "+UtilErros.getMensagemErro(e));
			e.printStackTrace();
		}

    }
}
