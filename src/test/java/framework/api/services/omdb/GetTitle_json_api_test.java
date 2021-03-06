package framework.api.services.omdb;

import framework.api.AbstractApiTestcase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static framework.api.services.ApiServiceManager.getResponseFromUriAsJsonString;
import static framework.api.misc.AvailableApiServices.OMDB;
import static framework.api.misc.OmdbApiTerms.*;
import static framework.utils.JsonUtils.getSpecificValueFromJSON;
import static framework.tags.TestcaseTags.*;

@Tag(API)
@Tag(API_OMDB)
public class GetTitle_json_api_test extends AbstractApiTestcase {

    @ParameterizedTest
    @ValueSource(strings = {"Shawshank","Spiderman","Nausicaa of the Valley of the Wind"})
    public void validateMovieExists(String title){
        String uriRequest = new OmdbUriRequest.omdbRequestBuilder(TITLE,title)
                .usingFormat(MOVIE)
                .usingDatatype(JSON_DATATYPE)
                .withPlotType(SHORT_PLOT)
                .withPageNumber(1)
                .build()
                .toString();
        // Make request and get the answer as a JSON string
        String request = getResponseFromUriAsJsonString(OMDB, uriRequest);
        //Make sure the title matches the expected value
        Assertions.assertTrue(getSpecificValueFromJSON(request,"Title").toLowerCase().contains(title.toLowerCase()),"Unable to find the requested title.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Game of Thrones","Bojack Horseman","Forged in Fire"})
    public void validateSeriesExists(String seriesTitle){
        String uriReq = new OmdbUriRequest.omdbRequestBuilder(TITLE,seriesTitle)
                .usingFormat(SERIES)
                .usingDatatype(JSON_DATATYPE)
                .withPlotType(FULL_PLOT)
                .build()
                .toString();
        String request = getResponseFromUriAsJsonString(OMDB, uriReq);
        //Make sure the title matches the expected value
        Assertions.assertTrue(getSpecificValueFromJSON(request,"Title").toLowerCase().contains(seriesTitle.toLowerCase()),"Unable to find the requested title.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"tt0087544"})
    public void validateSpecificItem(String id){
        String uriReq = new OmdbUriRequest.omdbRequestBuilder(ID,id)
                .build()
                .toString();
        String request = getResponseFromUriAsJsonString(OMDB, uriReq);
        Assertions.assertTrue(getSpecificValueFromJSON(request,"imdbID").contains(id));
    }
}
