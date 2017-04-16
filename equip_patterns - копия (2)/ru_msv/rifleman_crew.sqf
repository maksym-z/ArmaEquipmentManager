if (!isServer) exitWith {};
_unit = _this select 0;

_unit forceAddUniform 'rhs_uniform_vdv_emr_des';
_unit addItemToUniform 'ACE_MapTools';
_unit addItemToUniform 'ACE_EarPlugs';
_unit addItemToUniform 'ACE_Flashlight_XL50';
_unit addVest 'rhs_6b23_digi_crew';
for '_i' from 1 to 4 do {_unit addItemToVest 'rhs_30Rnd_545x39_7N22_AK';};
_unit addItemToVest 'rhs_mag_rdg2_black';
_unit addHeadgear 'rhs_tsh4';
_unit addWeapon 'rhs_weap_ak74m_folded';
_unit addPrimaryWeaponItem 'rhs_acc_dtk';
_unit linkItem 'ItemMap';
_unit linkItem 'ItemCompass';
_unit linkItem 'tf_microdagr';
_unit linkItem 'ItemRadio';
[_unit] call compile preprocessFileLineNumbers 'equip_patterns\ru_msv\ace_med.sqf';
