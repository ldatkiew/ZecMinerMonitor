package com.lda.zecminer.monitor.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GpuStat {
	
	private Long gpuid; // 0,
	private Long cudaid; // 0,
	private String busid; // "0000:01:00.0",
	private String name; // "GeForce GTX 1060 6GB",
	private Long gpu_status; // 2,
	private Long solver; // 0,
	private Long temperature; // 67,
	private Long gpu_power_usage; // 88,
	private Long speed_sps; // 288,
	private Long accepted_shares; // 1,
	private Long rejected_shares; // 0,
	private Long start_time; // 1497973765
	
	private int speedMinLimit = 100;
	
	public String forLogs()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("GPU=");
		builder.append(gpuid);
		builder.append(", speed_sps= >> ");
		builder.append(speed_sps);
		builder.append(" << , name=");
		builder.append(name);
		builder.append(", gpu_status=");
		builder.append(gpu_status);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", gpu_power_usage=");
		builder.append(gpu_power_usage);
		builder.append(", start_time=");
		builder.append(extractedStartDate());
		builder.append("]\n");
		return builder.toString();
	}

	public Date extractedStartDate() {
		return new Date(start_time * 1000);
	}
	
	public Long getGpuid() {
		return gpuid;
	}
	public void setGpuid(Long gpuid) {
		this.gpuid = gpuid;
	}
	public Long getCudaid() {
		return cudaid;
	}
	public void setCudaid(Long cudaid) {
		this.cudaid = cudaid;
	}
	public String getBusid() {
		return busid;
	}
	public void setBusid(String busid) {
		this.busid = busid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getGpu_status() {
		return gpu_status;
	}
	public void setGpu_status(Long gpu_status) {
		this.gpu_status = gpu_status;
	}
	public Long getSolver() {
		return solver;
	}
	public void setSolver(Long solver) {
		this.solver = solver;
	}
	public Long getTemperature() {
		return temperature;
	}
	public void setTemperature(Long temperature) {
		this.temperature = temperature;
	}
	public Long getGpu_power_usage() {
		return gpu_power_usage;
	}
	public void setGpu_power_usage(Long gpu_power_usage) {
		this.gpu_power_usage = gpu_power_usage;
	}
	public Long getSpeed_sps() {
		return speed_sps;
	}
	public void setSpeed_sps(Long speed_sps) {
		this.speed_sps = speed_sps;
	}
	public Long getAccepted_shares() {
		return accepted_shares;
	}
	public void setAccepted_shares(Long accepted_shares) {
		this.accepted_shares = accepted_shares;
	}
	public Long getRejected_shares() {
		return rejected_shares;
	}
	public void setRejected_shares(Long rejected_shares) {
		this.rejected_shares = rejected_shares;
	}
	public Long getStart_time() {
		return start_time;
	}
	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Result [gpuid=");
		builder.append(gpuid);
		builder.append(", cudaid=");
		builder.append(cudaid);
		builder.append(", busid=");
		builder.append(busid);
		builder.append(", name=");
		builder.append(name);
		builder.append(", gpu_status=");
		builder.append(gpu_status);
		builder.append(", solver=");
		builder.append(solver);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", gpu_power_usage=");
		builder.append(gpu_power_usage);
		builder.append(", speed_sps=");
		builder.append(speed_sps);
		builder.append(", accepted_shares=");
		builder.append(accepted_shares);
		builder.append(", rejected_shares=");
		builder.append(rejected_shares);
		builder.append(", start_time=");
		builder.append(start_time);
		builder.append("]");
		return builder.toString();
	}
	public boolean isWorking() {

		if(speed_sps > speedMinLimit)
		{
			return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accepted_shares == null) ? 0 : accepted_shares.hashCode());
		result = prime * result + ((busid == null) ? 0 : busid.hashCode());
		result = prime * result + ((cudaid == null) ? 0 : cudaid.hashCode());
		result = prime * result + ((gpu_power_usage == null) ? 0 : gpu_power_usage.hashCode());
		result = prime * result + ((gpu_status == null) ? 0 : gpu_status.hashCode());
		result = prime * result + ((gpuid == null) ? 0 : gpuid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rejected_shares == null) ? 0 : rejected_shares.hashCode());
		result = prime * result + ((solver == null) ? 0 : solver.hashCode());
		result = prime * result + ((speed_sps == null) ? 0 : speed_sps.hashCode());
		result = prime * result + ((start_time == null) ? 0 : start_time.hashCode());
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
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
		GpuStat other = (GpuStat) obj;
		if (accepted_shares == null) {
			if (other.accepted_shares != null)
				return false;
		} else if (!accepted_shares.equals(other.accepted_shares))
			return false;
		if (busid == null) {
			if (other.busid != null)
				return false;
		} else if (!busid.equals(other.busid))
			return false;
		if (cudaid == null) {
			if (other.cudaid != null)
				return false;
		} else if (!cudaid.equals(other.cudaid))
			return false;
		if (gpu_power_usage == null) {
			if (other.gpu_power_usage != null)
				return false;
		} else if (!gpu_power_usage.equals(other.gpu_power_usage))
			return false;
		if (gpu_status == null) {
			if (other.gpu_status != null)
				return false;
		} else if (!gpu_status.equals(other.gpu_status))
			return false;
		if (gpuid == null) {
			if (other.gpuid != null)
				return false;
		} else if (!gpuid.equals(other.gpuid))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rejected_shares == null) {
			if (other.rejected_shares != null)
				return false;
		} else if (!rejected_shares.equals(other.rejected_shares))
			return false;
		if (solver == null) {
			if (other.solver != null)
				return false;
		} else if (!solver.equals(other.solver))
			return false;
		if (speed_sps == null) {
			if (other.speed_sps != null)
				return false;
		} else if (!speed_sps.equals(other.speed_sps))
			return false;
		if (start_time == null) {
			if (other.start_time != null)
				return false;
		} else if (!start_time.equals(other.start_time))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		return true;
	}

	public void setSpeedMinLimit(int speedMinLimit) {
		this.speedMinLimit = speedMinLimit;
	}
	
}
