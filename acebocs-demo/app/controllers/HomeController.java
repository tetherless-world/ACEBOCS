package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.inject.Inject;

import forms.Query;
import models.Instrument;
import models.ItemConcept;
import models.Practice;
import models.QuestionnaireScale;
import play.data.Form;
import play.mvc.*;

import static play.libs.Scala.asScala;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject play.data.FormFactory formFactory;
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index(Http.Request request) {
        // List<Instrument> instruments = Instrument.getAll();
        // List<QuestionnaireScale> scales = QuestionnaireScale.getAll();
        return ok(views.html.index.render(request));
    }

    public Result query(Http.Request request) {
        Form<Query> queryForm = formFactory.form(Query.class);
        Query query = queryForm.bindFromRequest(request).get();
        System.out.println(query.getQuery());
        //System.out.println(query.getParameter());
        if (query.getQuery().equals("query0")) {
            List<Practice> practices = Practice.getAll();
            return ok(new Gson().toJson(practices));
        } else if (query.getQuery().equals("query1")) {
            QuestionnaireScale scale = QuestionnaireScale.getByUri(query.getParameter());
            List<ItemConcept> itemConcepts = ItemConcept.getByScale(scale);
            return ok(new Gson().toJson(itemConcepts));
        } else if (query.getQuery().equals("query2")) {
            Instrument instrument = Instrument.getByUri(query.getParameter());
            List<Instrument> sources = Instrument.getSource(instrument);
            return ok(new Gson().toJson(sources));
        } else if (query.getQuery().equals("query3")) {
            Instrument instrument = Instrument.getByUri(query.getParameter());
            List<Instrument> targets = Instrument.getTarget(instrument);
            return ok(new Gson().toJson(targets));
        } else if (query.getQuery().equals("query4")) {
            Instrument instrument0 = Instrument.getByUri(query.getParameter());
            Instrument instrument1 = Instrument.getByUri(query.getParameter1());
            List<ItemConcept> itemConcepts = ItemConcept.getByInstruments(instrument0, instrument1);
            return ok(new Gson().toJson(itemConcepts));
        }
        List<String> places = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
        return ok(new Gson().toJson(places));
    }
}
