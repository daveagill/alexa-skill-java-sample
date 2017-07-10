package sample.alexa.binaryconverter;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class ResponseHelper {
	/**
	 * Use to prompt a user and await a response from them,
	 * if they don't respond then Alexa will continually re-prompt them.
	 * @param initialSpeechText		The initial speech to prompt the user with.
	 * @param repromptSpeechText	The re-prompt speech, used if the user doesn't respond quickly enough.
	 */
	public static SpeechletResponse newPromptingResponse(String initialSpeechText, String repromptSpeechText) {
		PlainTextOutputSpeech initialSpeech = new PlainTextOutputSpeech();
		initialSpeech.setText(initialSpeechText);
		
		PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
		repromptSpeech.setText(repromptSpeechText);
		
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(repromptSpeech);
		
		return SpeechletResponse.newAskResponse(initialSpeech, reprompt);
	}
	
	/**
	 * Use to inform the user that the skill was unsuccessful for some reason.
	 * The session is kept alive in order to give the user a chance to re-try.
	 * @param speechText	Speech that is descriptive of the failure and asking the user to re-try.
	 */
	public static SpeechletResponse newFailureResponse(String speechText) {
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);
		SpeechletResponse response = SpeechletResponse.newTellResponse(speech);
		response.setShouldEndSession(false);
		return response;
 	}
	
	/**
	 * Use to produce an answer speech along with a corresponding card (visible in the Alexa app).
	 * @param cardTitle		The title of the card.
	 * @param cardText		The answer as it should appear on the card.
	 * @param speechText	The answer as it should be spoken aloud.
	 * @return
	 */
	public static SpeechletResponse newAnswerResponse(String cardTitle, String cardText, String speechText) {
		// create a card to display in the app
		SimpleCard card = new SimpleCard();
		card.setTitle(cardTitle);
		card.setContent(cardText);
		
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		SpeechletResponse response = SpeechletResponse.newTellResponse(speech, card);
		return response;
	}
}
