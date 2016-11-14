package co.com.ganso.nucleo.bean;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class SecurityListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7295180863227196331L;

	@Override
	public void afterPhase(PhaseEvent phaseEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforePhase(PhaseEvent phaseEvent) {
		if (phaseEvent.getPhaseId() == PhaseId.RESTORE_VIEW){
			if(SessionUtils.getParameterSession("credencial")==null){
				
			}
		}
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return null;
	}

}
