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
_unit forceAddUniform 'TRYK_U_B_BLKBLK_CombatUniform';
_unit addVest 'TRYK_V_Sheriff_BA_TB';
_unit addHeadgear 'TRYK_H_headsetcap_blk';
_unit linkItem 'ItemMap';
_unit linkItem 'ItemCompass';
_unit linkItem 'ItemWatch';
_unit linkItem 'ItemRadio';
[_unit] call bn_equip_cdf_ace_med;
