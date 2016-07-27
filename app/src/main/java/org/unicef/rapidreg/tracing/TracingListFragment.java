package org.unicef.rapidreg.tracing;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;

import org.unicef.rapidreg.IntentSender;
import org.unicef.rapidreg.R;
import org.unicef.rapidreg.base.RecordListAdapter;
import org.unicef.rapidreg.base.RecordListFragment;
import org.unicef.rapidreg.base.RecordListPresenter;
import org.unicef.rapidreg.model.RecordModel;
import org.unicef.rapidreg.model.Tracing;
import org.unicef.rapidreg.service.CaseFormService;
import org.unicef.rapidreg.service.RecordService;
import org.unicef.rapidreg.service.TracingService;

import java.util.Arrays;
import java.util.List;

import butterknife.OnClick;

public class TracingListFragment extends RecordListFragment {

    private static final SpinnerState[] SPINNER_STATES = {
            SpinnerState.INQUIRY_DATE_ASC,
            SpinnerState.INQUIRY_DATE_DES};

    private static final int DEFAULT_SPINNER_STATE_POSITION =
            Arrays.asList(SPINNER_STATES).indexOf(SpinnerState.INQUIRY_DATE_DES);

    @Override
    public RecordListPresenter createPresenter() {
        return new RecordListPresenter(RecordModel.TRACING);
    }

    @Override
    protected void initListContainer(final RecordListAdapter adapter) {
        super.initListContainer(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listContainer.setLayoutManager(layoutManager);
        listContainer.setAdapter(adapter);

        List<Tracing> tracings = TracingService.getInstance().getAll();
        int index = tracings.isEmpty() ? HAVE_NO_RESULT : HAVE_RESULT_LIST;
        viewSwitcher.setDisplayedChild(index);

    }

    @Override
    protected void initOrderSpinner(final RecordListAdapter adapter) {
        orderSpinner.setAdapter(new SpinnerAdapter(getActivity(),
                R.layout.record_list_spinner_opened, Arrays.asList(SPINNER_STATES)));
        orderSpinner.setSelection(DEFAULT_SPINNER_STATE_POSITION);
        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floatingMenu.collapse();
                handleItemSelection(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            private void handleItemSelection(int position) {
                TracingService service = TracingService.getInstance();
                switch (SPINNER_STATES[position]) {
                    case INQUIRY_DATE_ASC:
                        adapter.setRecordList(service.getAllOrderByDateASC());
                        break;
                    case INQUIRY_DATE_DES:
                        adapter.setRecordList(service.getAllOrderByDateDES());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick(R.id.add_case)
    public void onCaseAddClicked() {
        RecordService.clearAudioFile();
        floatingMenu.collapseImmediately();

        if (!CaseFormService.getInstance().isFormReady()) {
            showSyncFormDialog(getResources().getString(R.string.child_case));
            return;
        }

        new IntentSender().showCaseAddPage(getActivity());
    }
}
