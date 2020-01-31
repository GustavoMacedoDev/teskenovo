package controller.util;

import bean.LancamentoFacade;
import domain.Mesa;
import controller.util.util.JsfUtil;
import controller.util.util.JsfUtil.PersistAction;
import bean.MesaFacade;
import domain.Lancamento;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.SelectEvent;

@Named("mesaController")
@SessionScoped
public class MesaController implements Serializable {

    @EJB
    private bean.MesaFacade mesaFacade;
    
    @EJB
    private LancamentoFacade lancamentoFacade;
    
    private List<Mesa> items = null;
    private Mesa selected;
    
    private List<Lancamento> lancamentosByMesa;

    public MesaController() {
    }

    public Mesa getSelected() {
        return selected;
    }

    public void setSelected(Mesa selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MesaFacade getMesaFacade() {
        return mesaFacade;
    }

    public LancamentoFacade getLancamentoFacade() {
        return lancamentoFacade;
    }

    public Mesa prepareCreate() {
        selected = new Mesa();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MesaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MesaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MesaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Mesa> getItems() {
        if (items == null) {
            items = getMesaFacade().findAll();
        }
        
        return items;
    }
    
    
    public List<Lancamento> getLancamentosByMesa(String id) {
        
         System.out.println("Id : " + id);
        
        lancamentosByMesa = lancamentoFacade.getByMesa(id);
        
        System.out.println("lancamentos" + lancamentosByMesa);
        
        return lancamentosByMesa;
    }
    
    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getMesaFacade().edit(selected);
                } else {
                    getMesaFacade().remove(selected);
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

    public Mesa getMesa(java.lang.Long id) {
        return getMesaFacade().find(id);
    }

    public List<Mesa> getItemsAvailableSelectMany() {
        return getMesaFacade().findAll();
    }

    public List<Mesa> getItemsAvailableSelectOne() {
        return getMesaFacade().findAll();
    }
    

    @FacesConverter(forClass = Mesa.class)
    public static class MesaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MesaController controller = (MesaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mesaController");
            return controller.getMesa(getKey(value));
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
            if (object instanceof Mesa) {
                Mesa o = (Mesa) object;
                return getStringKey(o.getMesaId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Mesa.class.getName()});
                return null;
            }
        }

    }
    
    public void onRowSelect(SelectEvent<Mesa> event) {
        FacesMessage msg;
        msg = new FacesMessage("Car Selected", event.getObject().getMesaId().toString());
        
        System.out.println("" + event.getObject().getMesaId().toString());
        
        String id = event.getObject().getMesaId().toString();
        
        getLancamentosByMesa(id);
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    

}
