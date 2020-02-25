package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.Lancamento;
import br.com.macedo.sistemas.util.JsfUtil;
import br.com.macedo.sistemas.util.JsfUtil.PersistAction;
import br.com.macedo.sistemas.bean.LancamentoFacade;
import br.com.macedo.sistemas.helper.UtilRelatorios;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Named("lancamentoController")
@SessionScoped
public class LancamentoController implements Serializable {

    @EJB
    private br.com.macedo.sistemas.bean.LancamentoFacade lancamentoFacade;
    private List<Lancamento> items = null;
    private Lancamento selected;
    
    private List<Lancamento> lancamentosByMesa;

    
    public LancamentoController() {
    }

    public Lancamento getSelected() {
        return selected;
    }

    public void setSelected(Lancamento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LancamentoFacade getFacade() {
        return lancamentoFacade;
    }

    public Lancamento prepareCreate() {
        selected = new Lancamento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LancamentoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LancamentoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LancamentoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Lancamento> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            
            try {
                if (persistAction != PersistAction.DELETE) {
                    lancamentoFacade.create(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Lancamento getLancamento(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Lancamento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Lancamento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Lancamento.class)
    public static class LancamentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LancamentoController controller = (LancamentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "lancamentoController");
            return controller.getLancamento(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Lancamento) {
                Lancamento o = (Lancamento) object;
                return getStringKey(o.getIdLancamento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Lancamento.class.getName()});
                return null;
            }
        }

    }
    
    public void relatorio(){
		HashMap parametros = new HashMap();
                System.out.println("rela" + items);
                
		UtilRelatorios.imprimeRelatorio("pedido", parametros, items);
	}
    
}
