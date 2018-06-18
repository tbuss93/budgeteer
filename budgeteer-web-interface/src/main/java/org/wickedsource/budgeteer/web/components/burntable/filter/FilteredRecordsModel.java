package org.wickedsource.budgeteer.web.components.burntable.filter;

import com.querydsl.core.types.Predicate;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.Pageable;
import org.wickedsource.budgeteer.service.record.RecordService;
import org.wickedsource.budgeteer.service.record.WorkRecord;
import org.wickedsource.budgeteer.service.record.WorkRecordFilter;
import org.wickedsource.budgeteer.service.record.WorkRecordQueries;

import java.util.List;

import static org.wickedsource.budgeteer.service.record.WorkRecordQueries.findByFilter;

public class FilteredRecordsModel extends LoadableDetachableModel<List<WorkRecord>> {

    @SpringBean
    private RecordService service;

    private WorkRecordFilter filter;
    private Pageable pageable;

    public FilteredRecordsModel(WorkRecordFilter filter, Pageable pageable) {
        Injector.get().inject(this);
        this.filter = filter;
        this.pageable = pageable;
    }

    public FilteredRecordsModel(WorkRecordFilter filter) {
        Injector.get().inject(this);
        this.filter = filter;
        this.pageable = null;
    }


    @Override
    protected List<WorkRecord> load() {
        if (pageable == null)
            return service.getFilteredRecords(filter);
        else
            return service.getFilteredWorkRecordByPage(filter, pageable);
    }

    public WorkRecordFilter getFilter() {
        return filter;
    }

    public void setFilter(WorkRecordFilter filter) {
        this.filter = filter;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

}
