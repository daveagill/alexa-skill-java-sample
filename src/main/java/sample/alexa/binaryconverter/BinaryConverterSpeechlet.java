package sample.alexa.binaryconverter;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;

/**
 * Speechlet for handling the life-cycle of the Alexa skill.
 */
public class BinaryConverterSpeechlet implements SpeechletV2 {

	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
		// do init here
	}
	
	@Override
	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
		// do cleanup here
	}

	@Override
	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
		String welcomeSpeech = "Welcome to Binary Converter. Ask me to convert a number to binary.";
		String repromptSpeech = "Which number would you like to convert to binary?";
		
		return ResponseHelper.newPromptingResponse(welcomeSpeech, repromptSpeech);
	}

	@Override
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		final Intent intent = requestEnvelope.getRequest().getIntent();
		
		// dispatch to the correct intent handling logic
		switch (intent.getName()) {
			case "DecimalToBinaryIntent": return handleDecimalToBinaryIntent(intent);
		}
		
		return ResponseHelper.newFailureResponse("Sorry I did not understand that, please try again");
	}


	private SpeechletResponse handleDecimalToBinaryIntent(Intent intent) {
		// extract the slot value (note: will be null if slot was not populated)
		String decimalSlotValue = intent.getSlot("number").getValue();
		
		// convert slot to an integer value, will fail if the slot is null or not a valid integer
		int number = 0;
		try {
			number = Integer.parseInt(decimalSlotValue);
		}
		catch (NumberFormatException nfe) {
			return ResponseHelper.newFailureResponse("Sorry I did not hear the value you wanted to convert, please try again");
		}
		
		// convert to binary
		String binary = Integer.toBinaryString(number);
		
		// force Alexa to pronounce each binary digit individually, e.g: "0, 1, 1, 0..."
		String speakableBinary = String.join(", ", binary.split(""));
		
		
		String cardTitle = "Convert " + number + " to binary";
		String cardText = "The number " + number + " in binary is: " + binary;
		String speech = "The number " + number + " converted into binary is: " + speakableBinary;
		return ResponseHelper.newAnswerResponse(cardTitle, cardText, speech);
	}
}
