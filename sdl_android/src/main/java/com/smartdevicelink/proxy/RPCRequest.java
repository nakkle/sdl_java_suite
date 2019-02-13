/**
 * 
 */
package com.smartdevicelink.proxy;

import java.util.Hashtable;

import com.smartdevicelink.proxy.rpc.listeners.OnRPCResponseListener;
import com.smartdevicelink.util.CorrelationIdGenerator;

public class RPCRequest extends RPCMessage {

	protected OnRPCResponseListener onResponseListener;

	public RPCRequest(String functionName) {
		super(functionName, RPCMessage.KEY_REQUEST);
		messageType = RPCMessage.KEY_REQUEST;
	}

	/**
	 *<p>Constructs a newly allocated RPCRequest object using a RPCRequest object</p>
	 *@param rpcMsg The {@linkplain RPCMessage} to use
	 */
	public RPCRequest(RPCMessage rpcMsg) {
		super(preprocessMsg(rpcMsg));
	}

	public RPCRequest(Hashtable<String, Object> hash) {
		super(hash);
	}

	public RPCRequest(RPCRequest request){
		super(request);
		setCorrelationID(CorrelationIdGenerator.generateId());
	}

	static RPCMessage preprocessMsg (RPCMessage rpcMsg) {
		if (rpcMsg.getMessageType() != RPCMessage.KEY_REQUEST) {
			rpcMsg.messageType = RPCMessage.KEY_REQUEST;
		}

		return rpcMsg;
	}

	public Integer getCorrelationID() {
		//First we check to see if a correlation ID is set. If not, create one.
		if(!function.containsKey(RPCMessage.KEY_CORRELATION_ID)){
			setCorrelationID(CorrelationIdGenerator.generateId());
		}
		return (Integer)function.get(RPCMessage.KEY_CORRELATION_ID);
	}
	
	public void setCorrelationID(Integer correlationID) {
		if (correlationID != null) {
            function.put(RPCMessage.KEY_CORRELATION_ID, correlationID );
        } else {
        	function.remove(RPCMessage.KEY_CORRELATION_ID);
        }
	}
    public void setOnRPCResponseListener(OnRPCResponseListener listener){
    	onResponseListener = listener;
    }
    
    public OnRPCResponseListener getOnRPCResponseListener(){
    	return this.onResponseListener;
    }
}
