package com.lda.zecminer.monitor.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MinerStat {

	private String method; // "getstat",
	private String error; // null,
	private Long start_time; // 1497973764,
	private String current_server; // "eu1-zcash.flypool.org:3333",
	private Long available_servers; // 1,
	private Long server_status; // 2,

	@JsonProperty("result")
	private List<GpuStat> gpusStat = new ArrayList<>();

	public static MinerStat createEmptyStat() {
		MinerStat minerStat = new MinerStat();
		minerStat.setError("Created empty stat");
		return minerStat;
	}
	
	public String forLog() {
		StringBuilder builder = new StringBuilder();
		builder.append("MinerStat: ");
		builder.append(" error=");
		builder.append(error);
		builder.append(", server_status=");
		builder.append(server_status);
		builder.append(", GPU's=\n");
		for (GpuStat gpuStat : gpusStat) {
			builder.append(gpuStat.forLogs());
		}
		builder.append("]");
		return builder.toString();
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Long getStart_time() {
		return start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	public String getCurrent_server() {
		return current_server;
	}

	public void setCurrent_server(String current_server) {
		this.current_server = current_server;
	}

	public Long getAvailable_servers() {
		return available_servers;
	}

	public void setAvailable_servers(Long available_servers) {
		this.available_servers = available_servers;
	}

	public Long getServer_status() {
		return server_status;
	}

	public void setServer_status(Long server_status) {
		this.server_status = server_status;
	}

	public List<GpuStat> getGpusStat() {
		return gpusStat;
	}

	public void setGpusStat(List<GpuStat> gpusStat) {
		this.gpusStat = gpusStat;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MinerStat [method=");
		builder.append(method);
		builder.append(", error=");
		builder.append(error);
		builder.append(", start_time=");
		builder.append(start_time);
		builder.append(", current_server=");
		builder.append(current_server);
		builder.append(", available_servers=");
		builder.append(available_servers);
		builder.append(", server_status=");
		builder.append(server_status);
		builder.append(", results=");
		builder.append(gpusStat);
		builder.append("]");
		return builder.toString();
	}

	public boolean isAllWorking() {

		boolean allWorks = true;
		if (gpusStat.isEmpty()) {
			allWorks = false;
		}

		for (GpuStat result : gpusStat) {
			allWorks &= result.isWorking();
		}
		return allWorks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((available_servers == null) ? 0 : available_servers.hashCode());
		result = prime * result + ((current_server == null) ? 0 : current_server.hashCode());
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((gpusStat == null) ? 0 : gpusStat.hashCode());
		result = prime * result + ((server_status == null) ? 0 : server_status.hashCode());
		result = prime * result + ((start_time == null) ? 0 : start_time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MinerStat other = (MinerStat) obj;
		if (available_servers == null) {
			if (other.available_servers != null)
				return false;
		} else if (!available_servers.equals(other.available_servers))
			return false;
		if (current_server == null) {
			if (other.current_server != null)
				return false;
		} else if (!current_server.equals(other.current_server))
			return false;
		if (error == null) {
			if (other.error != null)
				return false;
		} else if (!error.equals(other.error))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (gpusStat == null) {
			if (other.gpusStat != null)
				return false;
		} else if (!gpusStat.equals(other.gpusStat))
			return false;
		if (server_status == null) {
			if (other.server_status != null)
				return false;
		} else if (!server_status.equals(other.server_status))
			return false;
		if (start_time == null) {
			if (other.start_time != null)
				return false;
		} else if (!start_time.equals(other.start_time))
			return false;
		return true;
	}

	public void setSpeedMinLimit(int speedMinLimit) {
		for (GpuStat gpuStat : gpusStat) {
			gpuStat.setSpeedMinLimit(speedMinLimit);
		}
	}
	
	

}

