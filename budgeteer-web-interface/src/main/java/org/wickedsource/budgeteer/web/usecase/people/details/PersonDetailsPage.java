package org.wickedsource.budgeteer.web.usecase.people.details;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wickedsource.budgeteer.web.Mount;
import org.wickedsource.budgeteer.web.usecase.base.BasePage;
import org.wickedsource.budgeteer.web.usecase.base.component.breadcrumb.Breadcrumb;
import org.wickedsource.budgeteer.web.usecase.base.component.breadcrumb.BreadcrumbsModel;
import org.wickedsource.budgeteer.web.usecase.dashboard.DashboardPage;
import org.wickedsource.budgeteer.web.usecase.people.details.component.budgetdistributionchart.BudgetDistributionChart;
import org.wickedsource.budgeteer.web.usecase.people.details.component.budgetdistributionchart.BudgetDistributionChartModel;
import org.wickedsource.budgeteer.web.usecase.people.details.component.highlightspanel.PersonHighlightsModel;
import org.wickedsource.budgeteer.web.usecase.people.details.component.highlightspanel.PersonHighlightsPanel;
import org.wickedsource.budgeteer.web.usecase.people.edit.EditPersonPage;
import org.wickedsource.budgeteer.web.usecase.people.overview.PeopleOverviewPage;
import org.wickedsource.budgeteer.web.usecase.people.weekreport.PersonWeekReportPage;
import org.wickedsource.budgeteer.web.wickedcharts.BudgeteerChartTheme;

@Mount("people/details/${id}")
public class PersonDetailsPage extends BasePage {


    public PersonDetailsPage(PageParameters parameters) {
        super(parameters);
        add(new PersonHighlightsPanel("highlightsPanel", new PersonHighlightsModel(getPersonId())));
        add(new BudgetDistributionChart("distributionChart", new BudgetDistributionChartModel(getPersonId()), new BudgeteerChartTheme()));
        add(createEditPersonLink("editPersonLink1"));
        add(createEditPersonLink("editPersonLink2"));
        add(new BookmarkablePageLink<PersonWeekReportPage>("weekReportLink1", PersonWeekReportPage.class, PersonWeekReportPage.createParameters(getPersonId())));
        add(new BookmarkablePageLink<PersonWeekReportPage>("weekReportLink2", PersonWeekReportPage.class, PersonWeekReportPage.createParameters(getPersonId())));
    }

    private Link createEditPersonLink(String id) {
        return new Link(id) {
            @Override
            public void onClick() {
                WebPage page = new EditPersonPage(EditPersonPage.createParameters(getPersonId()), PersonDetailsPage.class, getPageParameters());
                setResponsePage(page);
            }
        };
    }

    /**
     * Creates a valid PageParameters object to pass into the constructor of this page class.
     *
     * @param personId id of the person whose details to display.
     * @return a valid PageParameters object.
     */
    public static PageParameters createParameters(long personId) {
        PageParameters parameters = new PageParameters();
        parameters.add("id", personId);
        return parameters;
    }

    private long getPersonId() {
        return getPageParameters().get("id").toLong();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected BreadcrumbsModel getBreadcrumbsModel() {
        BreadcrumbsModel model = new BreadcrumbsModel(DashboardPage.class, PeopleOverviewPage.class);
        model.addBreadcrumb(new Breadcrumb(PersonDetailsPage.class, getPageParameters(), new PersonNameModel(getPersonId())));
        return model;
    }

}
