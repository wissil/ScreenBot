package com.util.ai.screenbot.bookie.core.gui.elements;

import com.util.ai.screenbot.bookie.core.gui.VBGuiElement;
import com.util.ai.screenbot.bookie.core.gui.common.VBBetInfoGui;
import com.util.ai.screenbot.bookie.core.gui.common.VBBrowsingStatusGui;
import com.util.ai.screenbot.bookie.core.gui.common.VBSingleBetGui;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreter;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBalanceGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerMaxStakeGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerMinStakeGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerOddsGui;

import static com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider.balanceInterpreter;
import static com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider.betInfoInterpreter;
import static com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider.bookmakerMaxStakeInterpreter;
import static com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider.bookmakerMinStakeInterpreter;
import static com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider.bookmakerOddsInterpreter;
import static com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider.browsingStatusInterpreter;
import static com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider.singleBetInterpreter;


public enum VBScreenElementType {
	
	BOOKMAKER_MIN_STAKE() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBookmakerMinStakeElement, ? super VBBookmakerMinStakeGui> getInterpreter() {
			return bookmakerMinStakeInterpreter();
		}
	},
	
	BOOKMAKER_MAX_STAKE() {
				
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBookmakerMaxStakeElement, ? super VBBookmakerMaxStakeGui> getInterpreter() {
			return bookmakerMaxStakeInterpreter();
		}
	},
	
	BROWSING_STATUS() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBrowsingStatusElement, VBBrowsingStatusGui> getInterpreter() {
			return browsingStatusInterpreter();
		}
	},
	
	BALANCE() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBalanceElement, ? super VBBalanceGui> getInterpreter() {
			return balanceInterpreter();
		}
	},

	SINGLE_BET() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBSingleBetElement, VBSingleBetGui> getInterpreter() {
			return singleBetInterpreter();
		}
	},
	
	BOOKMAKER_ODDS() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBookmakerOddsElement, ? super VBBookmakerOddsGui> getInterpreter() {
			return bookmakerOddsInterpreter();
		}
	},
	
	BET_INFO() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBetInfoElement, VBBetInfoGui> getInterpreter() {
			return betInfoInterpreter();
		}
	};
	
	public <E extends VBScreenElement, G extends VBGuiElement> VBElementInterpreter<E, G> getInterpreter() {
		// should be overriden in enum values
		return null;
	}
}
