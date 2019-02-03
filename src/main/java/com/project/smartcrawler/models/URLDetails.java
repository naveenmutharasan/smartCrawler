package com.project.smartcrawler.models;

public class URLDetails {
	private String Uri;
	private String protocol;
	private boolean reachable = true;
	private String unreachableReason = "";
	private boolean internal = false;
	private boolean isRedirected;
	private String redirectedURI = "";
	private Integer uriDepth;
	
	public Integer getUriDepth() {
		return uriDepth;
	}
	public void setUriDepth(Integer uriDepth) {
		this.uriDepth = uriDepth;
	}
	public String getUri() {
		return Uri;
	}
	public void setUri(String uri) {
		Uri = uri;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public boolean isReachable() {
		return reachable;
	}
	public void setReachable(boolean reachable) {
		this.reachable = reachable;
	}
	public String getUnreachableReason() {
		return unreachableReason;
	}
	public void setUnreachableReason(String unreachableReason) {
		this.unreachableReason = unreachableReason;
	}
	public boolean isInternal() {
		return internal;
	}
	public void setInternal(boolean internal) {
		this.internal = internal;
	}
	public boolean isRedirected() {
		return isRedirected;
	}
	public void setRedirected(boolean isRedirected) {
		this.isRedirected = isRedirected;
	}
	public String getRedirectedURI() {
		return redirectedURI;
	}
	public void setRedirectedURI(String redirectedURI) {
		this.redirectedURI = redirectedURI;
	}
	
	@Override
	public boolean equals(Object second) {
		if (second instanceof URLDetails) {
			return Uri.equals(((URLDetails) second).getUri());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.Uri.hashCode();
	}
}
