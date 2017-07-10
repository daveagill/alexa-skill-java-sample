# Alexa Skill Starter Template for Java
***
***--- Fork this repository to get started writing Alexa skills in Java.***
***
This is a basic Alexa skill that simply converts an integer value into binary.
Written in Java 8 using Amazon's new SpeechletV2 API, runs on AWS Lambda.
Use as a basis for more advanced skills!

#### Example Interaction:
> [User]: Alexa ask Binary Converter to convert the number twenty three

> [Alexa]: The number 23 converted into binary is: 1, 0, 1, 1

The Interaction Model is defined by `intent-schema.json` and `sample-utterances.txt`.

## Getting Started
### 1) Building the Project
This is a Maven project that uses Maven Shade to package all dependencies into a single Jar suitable for deployment to AWS Lambda. Once built the generated Jar file will be: `[project root]/target/binary-converter-0.0.1-SNAPSHOT.jar`.

#### How to build from Eclipse:
1. **Import into workspace:**
    * File -> Import -> Maven -> Existing Maven Projects
    * This should create a project called 'BinaryConverterExampleAlexaSkill'
2. **Configure and run:**
    * Right click project -> Run As -> Maven Build...
    * In the 'goals' textbox type: `package`
    * Click 'Run'

### How to build from the command-line:
**Run this command:**
`mvn package`
*Note: Maven must be installed as a commandline tool.*

### 2) Deploy .jar to AWS Lambda
1. Register for AWS and sign in to the AWS Console
2. On the top-right of the page ensure the region is set to **US East (N. Virginia)**. (Alexa only supports regions in North America and Europe).
3. Click Services -> Compute -> Lambda
4. Click '**Create a Lambda function**'
5. Select '**Blank Function**;
6. Under 'Configure triggers' choose '**Alexa Skills Kit**' as the trigger.
7. Under 'Configure function':
    1. Give it a name (e.g. 'BinaryConverterAlexaSkill')
    2. Select '**Java 8**' as the Runtime
    3. Upload the Jar: `[project root]/target/binary-converter-0.0.1-SNAPSHOT.jar`
    4. Set the 'Handler' to the request-stream-handler class: `sample.alexa.binaryconverter.AlexaSpeechletRequestStreamHandler`
    5. Configure a Role:
        * Select '**Create new role from template(s)**'
        * Give the role a name (e.g. 'alexa_skill_basic_role')
        * Leave 'Policy templates' blank because this skill requires no special permissions.
8. Click Next to review and finish.

### 3) Define the Alexa Skill
1. Register for an Amazon Developer account and sign in to the Developer Console.
2. Click 'Alexa' tab
3. Click to '**Get started**' under 'Alexa Skills Kit'
4. Click '**Add a new skill**'
5. Provide Skill Information:
    1. Set 'Skill Type' to  '**Custom Interaction Model**'
    2. Set 'Language' to either 'English (U.S.)' or 'English (U.K.)'
    3. Give it a name (e.g. 'Binary Converter')
    4. Give it an invocation name to use as the trigger phrase (e.g. 'binary converter')
    5. Click Next.
6. Provide the Interaction Model:
    1. Paste the contents of `intent-schema.json` into the '**Intent Schema**' text area.
    2. Paste the contents of `sample-utterances.txt` into the '**Sample Utterances**' text area.
    3. (Leave custom slot types empty - this skill does not need them)
7. Click Next to move onto the Configuration tab and follow the instuctions below...

### 4) Associate the Alexa Skill to the AWS Lambda service
1. Configure the skill to invoke the Lambda service:
    1. On the Configuration tab for the skill set 'Service Endpoint Type' to: '**AWS Lambda ARN (Amazon Resource Name)**'
    2. Select the region that your Lambda function is hosted under (North America)
    3. Paste the **ARN** of the Lambda function into the textbox.
       _For help finding the ARN see **(Tip 1)** below._
    4. Click Next to move onto the Testing page.
4. Configure the Lambda function to respond to the Skill ID:
    1. In the AWS console on the '**Code**' tab of the function add an Environment Variable:
        1. Set the key to: `alexa_skill_id`
        2. Set the value to the **ID** of the Alexa skill.
           _For helping finding the ID see **(Tip 2)** below._
    2. Click the '**Save**' button.

***(Tip 1): How to find the ARN of your AWS Lambda Function:***
1. In AWS Console click Services -> Compute -> Lambda
2. Select your Lambda function from the list of Functions.
3. Note the **ARN** of the function at the top of the page.

***(Tip 2): How to find the ID of your Alexa Skill:***
1. In the Amazon Developer Console click your Alexa Skill
2. Note the **ID** of the skill at the top of the page.

### 5) That's it! --- Test out the skill
* On the 'Test' page of the Alexa skill you can send text-based utterances to see (and hear) the response.
* In the Alexa App you can activate the skill on to your Alexa device.

## Debugging
If the skill fails to execute then:
1. Re-trace the instructions here, incase you missed something.
2. Check the errors chart in the Lambda function by clicking on the Monitoring tab.
3. Check the AWS CloudWatch logs (there is a link to the logs under the Monitoring tab). If the are Java exceptions then you can get the stack-trace here.

## Contributing
Pull-Requests are welcome!
This skill is intented to serve as a simple 'starter' skill so I am interested in ways to cleanup, simplify, streamline and to make things more canonical.