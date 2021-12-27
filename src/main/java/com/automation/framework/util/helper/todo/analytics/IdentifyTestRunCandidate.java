package com.automation.framework.util.helper.todo.analytics;

/**
 * @Author - suryajit7
 * TODO: Use different analytical points to identify which tests should be run in build pipeline post any code change.
 * 1. Google Analytics data
 * 2. Identify test candidates by first finding which module(class, api method etc.) underwent any change and then find all the other modules which talk to this earlier identified module.
 * 3. Tests which are newly added.
 * 4. Tests which have failed in last few runs.
 * 5. A newly added tests should be run each hour in a 24 hours time frame to find out any timezone related issue.
 * 6. By default -> run all tests before any major release, once in a month, on calendar year change and on 29th Feb of Leap years.
 * 7. Any test which didn't get chance to run for 45 days for any reason.
 */
public class IdentifyTestRunCandidate {



}
