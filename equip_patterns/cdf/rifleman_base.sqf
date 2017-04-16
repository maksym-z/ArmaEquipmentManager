if (!isServer) exitWith {};
_unit = _this select 0;

removeAllWeapons _unit;
removeAllItems _unit;
removeAllAssignedItems _unit;
removeUniform _unit;
removeVest _unit;
removeBackpack _unit;
removeHeadgear _unit;
removeGoggles _unit;
_unit forceAddUniform 'LOP_U_CDF_Fatigue_01';
_unit addVest 'LOP_V_6B23_6Sh92_CDF';
_unit addItemToVest 'ACE_SpraypaintBlack';
_unit addItemToVest 'ACE_Flashlight_KSF1';
for '_i' from 1 to 2 do {_unit addItemToVest 'rhs_mag_rgd5';};
_unit addItemToVest 'rhs_mag_rdg2_white';
_unit addHeadgear 'LOP_H_6B27M_CDF';
_unit linkItem 'ItemCompass';
_unit linkItem 'ItemWatch';
_unit linkItem 'ItemRadio';
[_unit] call bn_equip_cdf_ace_med;