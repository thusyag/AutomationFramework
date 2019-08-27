package utils;

import net.rcarz.jiraclient.*;
import net.rcarz.jiraclient.Issue.FluentCreate;

public class JiraServiceProvider {

	public JiraClient jira;
	public String project;
//	public String assignee;

	public JiraServiceProvider(String jiraUrl, String username, String password, String project) {
		BasicCredentials creds = new BasicCredentials(username, password);
		jira = new JiraClient(jiraUrl, creds);
		this.project = project;
	}

	public void createJiraTicket(String issueType, String summary, String description,String assignee, String reporterName) {

		try {
			FluentCreate fleuntCreate = jira.createIssue(project, issueType);
			fleuntCreate.field(Field.SUMMARY, summary);
			fleuntCreate.field(Field.DESCRIPTION, description);
//			fleuntCreate.field(Field.ASSIGNEE, assignee);
			Issue newIssue = fleuntCreate.execute();
			System.out.println("new issue created in jira with ID: " + newIssue);

		} catch (JiraException e) {
			e.printStackTrace();
		}

	}

}
