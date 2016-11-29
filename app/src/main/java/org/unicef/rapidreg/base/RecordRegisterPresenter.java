package org.unicef.rapidreg.base;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import org.unicef.rapidreg.forms.CaseFormRoot;
import org.unicef.rapidreg.forms.Field;
import org.unicef.rapidreg.forms.RecordForm;
import org.unicef.rapidreg.model.CaseForm;
import org.unicef.rapidreg.service.CaseFormService;
import org.unicef.rapidreg.service.cache.ItemValuesMap;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

public class RecordRegisterPresenter extends MvpBasePresenter<RecordRegisterView> {

    private CaseFormService caseFormService;

    @Inject
    public RecordRegisterPresenter(CaseFormService caseFormService) {
        this.caseFormService = caseFormService;
    }

    public void initContext(Context context, List<Field> fields, boolean isMiniForm) {
        if (isViewAttached()) {
            RecordRegisterAdapter adapter = new RecordRegisterAdapter(context, removeSeparatorFields(fields),
                    new ItemValuesMap(), isMiniForm);
            getView().initView(adapter);
        }
    }

    private List<Field> removeSeparatorFields(List<Field> fields) {
        Iterator<Field> iterator = fields.iterator();

        while (iterator.hasNext()) {
            Field field = iterator.next();
            if (field.isSeparator()) {
                iterator.remove();
            }
        }

        return fields;
    }

    public CaseFormRoot getCurrentForm() {
        return caseFormService.getCurrentForm();
    }
}
