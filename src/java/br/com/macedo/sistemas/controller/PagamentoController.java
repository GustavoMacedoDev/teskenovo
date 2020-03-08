package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.bean.MesaFacade;
import br.com.macedo.sistemas.domain.Pagamento;
import br.com.macedo.sistemas.controller.util.JsfUtil;
import br.com.macedo.sistemas.controller.util.JsfUtil.PersistAction;
import br.com.macedo.sistemas.bean.PagamentoFacade;
import br.com.macedo.sistemas.domain.Mesa;

import java.io.Serializable;
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

@Named("pagamentoController")
@SessionScoped
public class PagamentoController implements Serializable {

    @EJB
    private PagamentoFacade ejbFacade;
    @EJB
    private MesaFacade mesaFacade;
    
    private List<Pagamento> items = null;
    private Pagamento selected;

    public PagamentoController() {
    }

    public Pagamento getSelected() {
        return selected;
    }

    public void setSelected(Pagamento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PagamentoFacade getFacade() {
        return ejbFacade;
    }

    public MesaFacade getMesaFacade() {
        return mesaFacade;
    }
    
    

    public Pagamento prepareCreate() {
        selected = new Pagamento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PagamentoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PagamentoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PagamentoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Pagamento> getItems() {
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
                    
                        
                    getFacade().edit(selected);
                    subtraiValor(selected);
                } else {
                    getFacade().remove(selected);
                    subtraiValorMesa(selected);
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

    public Pagamento getPagamento(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Pagamento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Pagamento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public void subtraiValor(Pagamento pagamento) {
        
        Long mesa = pagamento.getMesaMesaId().getMesaId();
        double valorPago = pagamento.getValorPago();
        
        getMesaFacade().insereValorMesa(mesa, valorPago);
    }
    
    public void subtraiValorMesa(Pagamento pagamento) {
        
        Long mesa = pagamento.getMesaMesaId().getMesaId();
        double valorPago = pagamento.getValorPago();
        
        getMesaFacade().subtraiValorMesa(mesa, valorPago);
        
    }

    @FacesConverter(forClass = Pagamento.class)
    public static class PagamentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PagamentoController controller = (PagamentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pagamentoController");
            return controller.getPagamento(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pagamento) {
                Pagamento o = (Pagamento) object;
                return getStringKey(o.getIdPagamento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pagamento.class.getName()});
                return null;
            }
        }

    }

}
