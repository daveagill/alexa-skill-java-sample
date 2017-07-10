package sample.alexa.binaryconverter;

import java.util.Collections;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * Main Entrypoint.
 * 
 * An AWS Lambda stream handler that dispatches to an Alexa speechlet.
 * Also validates and restricts usage to a specific an alexa_app_id provided from a Lambda environment variable.
 */
public class AlexaSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final String APPLICATION_ID = System.getenv("alexa_skill_id");

    public AlexaSpeechletRequestStreamHandler() {
        super(new BinaryConverterSpeechlet(), Collections.singleton(APPLICATION_ID));
    }
}