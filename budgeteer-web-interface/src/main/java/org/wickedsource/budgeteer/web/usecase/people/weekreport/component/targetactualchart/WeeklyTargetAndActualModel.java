package org.wickedsource.budgeteer.web.usecase.people.weekreport.component.targetactualchart;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IObjectClassAwareModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wickedsource.budgeteer.service.statistics.StatisticsService;
import org.wickedsource.budgeteer.service.statistics.TargetAndActual;

public class WeeklyTargetAndActualModel extends LoadableDetachableModel<TargetAndActual> implements IObjectClassAwareModel<TargetAndActual> {

    @SpringBean
    private StatisticsService service;

    private long personId;

    public WeeklyTargetAndActualModel(long personId) {
        Injector.get().inject(this);
        this.personId = personId;
    }

    @Override
    protected TargetAndActual load() {
        return service.getWeekStatsForPerson(personId, 12);
    }

    @Override
    public Class<TargetAndActual> getObjectClass() {
        return TargetAndActual.class;
    }
}
