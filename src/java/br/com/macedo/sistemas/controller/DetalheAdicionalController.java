package br.com.macedo.sistemas.controller;

import br.com.macedo.sistemas.domain.DetalheAdicional;
import br.com.macedo.sistemas.util.JsfUtil;
import br.com.macedo.sistemas.util.JsfUtil.PersistAction;
import br.com.macedo.sistemas.bean.DetalheAdicionalFacade;

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

@Named("detalheAdicionalController")
@SessionScoped
public class DetalheAdicionalController implements Serializable {

    @EJB
    private br.com.macedo.sistemas.bean.DetalheAdicionalFacade ejbFacade;
    private List<DetalheAdicional> items = null;
    private DetalheAdicional selected;

    public DetalheAdicionalController() {
    }

    public DetalheAdicional getSelected() {
        return selected;
    }

    public void setSelected(DetalheAdicional selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DetalheAdicionalFacade getFacade() {
        return ejbFacade;
    }

    public DetalheAdicional prepareCreate() {
        selected = new DetalheAdicional();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetalheAdicionalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetalheAdicionalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DetalheAdicionalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DetalheAdicional> getItems() {
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

    public DetalheAdicional getDetalheAdicional(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DetalheAdicional> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetalheAdicional> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DetalheAdicional.class)
    public static class DetalheAdicionalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalheAdicionalController controller = (DetalheAdicionalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalheAdicionalController");
            return controller.getDetalheAdicional(getKey(value));
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
            if (object instanceof DetalheAdicional) {
                DetalheAdicional o = (DetalheAdicional) object;
                return getStringKey(o.getIdDetalheAdicional());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetalheAdicional.class.getName()});
                return null;
            }
        }

    }

}
