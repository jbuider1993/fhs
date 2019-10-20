<template>
  <div class="logisContainer">
    <!--<div class="toolsBox clearfix">-->
      <!--<div class="fl">-->
        <!--<el-popover placement="top-start" width="120" popper-class="comDropdown" trigger="click">-->
          <!--<ul>-->
            <!--<li>-->
              <!--<span @click="changeMapType('tileLayer')">-->
                <!--<i-->
                  <!--:class="[mapType == 'tileLayer'?'a_iconfont a_icon-duihao':'e6-icon-minus_line']"-->
                <!--&gt;</i>平面地图-->
              <!--</span>-->
            <!--</li>-->
            <!--<li>-->
              <!--<span @click="changeMapType('satellite')">-->
                <!--<i-->
                  <!--:class="[mapType == 'satellite'?'a_iconfont a_icon-duihao':'e6-icon-minus_line']"-->
                <!--&gt;</i>卫星地图-->
              <!--</span>-->
            <!--</li>-->
          <!--</ul>-->
          <!--<span class="toolBtn" slot="reference">-->
            <!--<i class="a_iconfont a_icon-shijieditu"></i>-->
            <!--{{mapType == 'tileLayer'?'平面地图':'卫星地图'}}-->
          <!--</span>-->
        <!--</el-popover>-->
      <!--</div>-->
      <!--<div class="fr">-->
        <!--&lt;!&ndash; 智能搜索 &ndash;&gt;-->
        <!--<el-popover-->
          <!--width="310"-->
          <!--popper-class="intelligent"-->
          <!--trigger="manual"-->
          <!--v-model="intelligent.isPanelShow"-->
        <!--&gt;-->
          <!--<div class="content">-->
            <!--<i class="e6-icon-delete_line" @click="closePanel_intelligent"></i>-->
            <!--<div class="eBox" v-for="(item, type) in intelligent.result" :key="type">-->
              <!--<div class="top">-->
                <!--<h5>{{item.title}}</h5>-->
                <!--<span>-->
                  <!--<i-->
                    <!--class="e6-icon-refresh_fill"-->
                    <!--title="换一换"-->
                    <!--@click="changeAnother_intelligent(type)"-->
                    <!--v-show="item.datas.length > INTELLIGENT_NUM"-->
                  <!--&gt;</i>-->
                  <!--<i-->
                    <!--class="a_iconfont a_icon-gengduo-copy"-->
                    <!--title="更多"-->
                    <!--@click="showMore_intelligent(type)"-->
                  <!--&gt;</i>-->
                <!--</span>-->
              <!--</div>-->
              <!--<ul class="content">-->
                <!--<li-->
                  <!--v-for="sItem in item.aShow"-->
                  <!--:key="sItem.id + sItem.name"-->
                  <!--:title="sItem.name"-->
                  <!--@click="showDetails_intelligent(type, sItem.id)"-->
                <!--&gt;{{sItem.name}}</li>-->
              <!--</ul>-->
            <!--</div>-->
          <!--</div>-->
          <!--<span slot="reference">-->
            <!--<el-input-->
              <!--size="mini"-->
              <!--placeholder="智能搜索"-->
              <!--class="searchBox"-->
              <!--v-model="intelligent.searchTxt"-->
              <!--@keydown.native.enter="search_intelligent"-->
            <!--&gt;-->
              <!--<i slot="suffix" class="e6-icon-search_line" @click="search_intelligent"></i>-->
            <!--</el-input>-->
          <!--</span>-->
        <!--</el-popover>-->
        <!--&lt;!&ndash; 拉框查找 &ndash;&gt;-->
        <!--<el-popover placement="top-start" width="120" popper-class="comDropdown" trigger="click">-->
          <!--<ul>-->
            <!--<li>-->
              <!--<span @click="drawRectToSearch_pullRect(1, '找自己的车')">-->
                <!--<i class="e6-icon-truck_line"></i>找自己的车-->
              <!--</span>-->
            <!--</li>-->
            <!--<slot name="searchLoVehicles_pullRect"></slot>-->
            <!--&lt;!&ndash; <li>-->
              <!--<span @click="drawRectToSearch_pullRect(2, '找已经定位的车')">-->
                <!--<i class="e6-icon-location-service_line"></i>找已经定位的车-->
              <!--</span>-->
            <!--</li>&ndash;&gt;-->
          <!--</ul>-->
          <!--<span class="toolBtn" slot="reference" style="display: none">-->
            <!--<i class="a_iconfont a_icon-lakuangxuanze"></i>拉框查找-->
          <!--</span>-->
        <!--</el-popover>-->
        <!--&lt;!&ndash; 物流地图 &ndash;&gt;-->
        <!--<el-popover placement="top-start" popper-class="comDropdown" trigger="click">-->
          <!--<ul>-->
            <!--<li>-->
              <!--<span>-->
                <!--<i class="e6-icon-location_line"></i>点-->
              <!--</span>-->
              <!--<span>-->
                <!--<i class="e6-icon-add_line" @click="markOverlay('marker')"></i>-->
                <!--<i-->
                  <!--:class="['a_iconfont', mEyeShow?'a_icon-yanjing':'a_icon-yanjing1']"-->
                  <!--:title="mEyeShow?'点击隐藏':'点击显示'"-->
                  <!--@click="mEyeClick"-->
                <!--&gt;</i>-->
                <!--<el-popover-->
                  <!--placement="bottom"-->
                  <!--trigger="click"-->
                  <!--popper-class="logisSubPop"-->
                  <!--v-model="mLevelPopShow"-->
                <!--&gt;-->
                  <!--<ul>-->
                    <!--<li :class="[mLevel=='全国'?'active':'']" @click="mLevelChange('全国')">全国</li>-->
                    <!--<li :class="[mLevel=='省'?'active':'']" @click="mLevelChange('省')">省</li>-->
                    <!--<li :class="[mLevel=='市'?'active':'']" @click="mLevelChange('市')">市</li>-->
                    <!--<li :class="[mLevel=='区'?'active':'']" @click="mLevelChange('区')">区</li>-->
                  <!--</ul>-->
                  <!--<a href="#" title="点击切换显示等级" slot="reference">({{mLevel}})</a>-->
                <!--</el-popover>-->
              <!--</span>-->
            <!--</li>-->
            <!--<li>-->
              <!--<span>-->
                <!--<i class="a_iconfont a_icon-polygon_draw"></i>区域-->
              <!--</span>-->
              <!--<span>-->
                <!--<i class="e6-icon-add_line" title="添加" @click="openMarkTypeLayer(false)"></i>-->
                <!--<i-->
                  <!--:class="['a_iconfont', aEyeShow?'a_icon-yanjing':'a_icon-yanjing1']"-->
                  <!--:title="aEyeShow?'点击隐藏':'点击显示'"-->
                  <!--@click="aEyeClick"-->
                <!--&gt;</i>-->
                <!--<el-popover-->
                  <!--placement="bottom"-->
                  <!--trigger="click"-->
                  <!--popper-class="logisSubPop"-->
                  <!--v-model="aLevelPopShow"-->
                <!--&gt;-->
                  <!--<ul>-->
                    <!--<li :class="[aLevel=='全国'?'active':'']" @click="aLevelChange('全国')">全国</li>-->
                    <!--<li :class="[aLevel=='省'?'active':'']" @click="aLevelChange('省')">省</li>-->
                    <!--<li :class="[aLevel=='市'?'active':'']" @click="aLevelChange('市')">市</li>-->
                    <!--<li :class="[aLevel=='区'?'active':'']" @click="aLevelChange('区')">区</li>-->
                  <!--</ul>-->
                  <!--<a href="#" title="点击切换显示等级" slot="reference">({{aLevel}})</a>-->
                <!--</el-popover>-->
              <!--</span>-->
            <!--</li>-->
            <!--&lt;!&ndash; <li>-->
              <!--<span>-->
                <!--<i class="e6-icon-line_line"></i>线路-->
              <!--</span>-->
              <!--<span>-->
                <!--<i-->
                  <!--:class="['a_iconfont', lEyeShow?'a_icon-yanjing':'a_icon-yanjing1']"-->
                  <!--:title="lEyeShow?'点击隐藏':'点击显示'"-->
                  <!--@click="lEyeClick"-->
                <!--&gt;</i>-->
              <!--</span>-->
            <!--</li>&ndash;&gt;-->
            <!--<li>-->
              <!--<span @click="openLayer_navigation">-->
                <!--<i class="a_iconfont a_icon-daohang2"></i>导航-->
              <!--</span>-->
            <!--</li>-->
            <!--<li>-->
              <!--<span @click="showTraffic">-->
                <!--<i class="a_iconfont a_icon-toggle"></i>路况-->
              <!--</span>-->
            <!--</li>-->
          <!--</ul>-->
          <!--<span class="toolBtn" slot="reference">-->
            <!--<i class="a_iconfont a_icon-ditu"></i>物流地图-->
          <!--</span>-->
        <!--</el-popover>-->
        <!--<span class="toolBtn" @click="openRangingTool">-->
          <!--<i class="a_iconfont a_icon-ceju4"></i>测距-->
        <!--</span>-->
        <!--&lt;!&ndash;<span class="toolBtn" @click="fullScreen">-->
          <!--<i :class="['a_iconfont',isFullScreen?'a_icon-quanping3':'a_icon-quanping2']"></i>全屏-->
        <!--</span>&ndash;&gt;-->
      <!--</div>-->
    <!--</div>-->
    <div class="mapContainer">
      <el-amap vid="amapContainer"></el-amap>
      <!-- 路况层 -->
      <div class="trafficLayer" v-show="isTrafficShow">
        <i class="e6-icon-delete_fill" @click="closeTraffic"></i>
        <el-card shadow="always" class="trafficTip">
          <label>拥堵</label>
          <em class="red"></em>
          <em class="yellow"></em>
          <em class="green"></em>
          <label>畅通</label>
          <i class="e6-icon-refresh_fill" title="刷新" @click="refreshTraffic"></i>
        </el-card>
      </div>
      <!-- 拉框查找 -->
      <e6-box
        class="commonLayer"
        ref="pullRectLayer"
        :title="'拉框查找 - '+ pullRect.title"
        @closeFun="closeListLayer_pullRect"
      >
        <div class="comContent">
          <p class="topStat">
            区域内的车辆数：{{pullRect.datas.length}} 辆
            <el-button type="primary" size="mini" @click="exportExcel_pullRect">导出Excel</el-button>
          </p>
          <el-table
            :data="pullRect.datas"
            max-height="200"
            :fit="true"
            @row-click="rowClick_pullRect"
          >
            <el-table-column label="序号" type="index" width="55" header-align="center"></el-table-column>
            <el-table-column label="车牌" prop="name" width="100" header-align="center"></el-table-column>
            <el-table-column
              label="位置"
              prop="position"
              width="300"
              :show-overflow-tooltip="true"
              header-align="center"
            ></el-table-column>
            <el-table-column label="定位时间" prop="gpsTime" width="150" header-align="center"></el-table-column>
            <el-table-column
              label="车辆状态"
              prop="gpsStatus"
              width="200"
              :show-overflow-tooltip="true"
              header-align="center"
            ></el-table-column>
            <el-table-column
              label="报警状态"
              prop="alarmStatus"
              width="200"
              :show-overflow-tooltip="true"
              header-align="center"
            ></el-table-column>
          </el-table>
        </div>
      </e6-box>
      <!-- 智能搜索 -->
      <e6-box
        class="editLayer intelligentLayer"
        ref="moreLayer_intelligent"
        title="智能搜索"
        @closeFun="closeMoreLayer_intelligent"
      >
        <div class="intelligentMore">
          <div class="oneOption" v-for="(item, index) in intelligent.moreList" :key="index">
            <h3>{{index + 1}}</h3>
            <p @click="showDetails_intelligent(intelligent.moreType, item.id)">
              <a href="#">{{item.name}}</a>
            </p>
            <p>{{item.address}}</p>
          </div>
        </div>
      </e6-box>
      <!-- 标注类型选择层 -->
      <div class="cBox" v-show="markTypeLayerShow">
        <i class="e6-icon-delete_line" @click="closeMarkTypeLayer"></i>
        <ul>
          <li v-show="markMarkerShow" @click="markOverlay('marker')">
            <!-- <i class="e6-icon-location_fill"></i> -->
            <img src="@/assets/images/map/marker.png">
            <span>点</span>
          </li>
          <li @click="markOverlay('rectangle')">
            <img src="@/assets/images/map/rect.png">
            <span>矩形</span>
          </li>
          <li @click="markOverlay('polygon')">
            <img src="@/assets/images/map/polygon.png">
            <span>多边形</span>
          </li>
        </ul>
      </div>
      <!-- 添加marker详情层 -->
      <e6-box
        class="editLayer"
        ref="markerLayer"
        :title="operType == 1?'修改点':'添加点'"
        @closeFun="mCloseLayer"
      >
        <div class="tContent">
          <el-form
            :model="mParams"
            :rules="rules"
            ref="form_marker"
            label-width="80px"
            class="formLayer"
          >
            <el-form-item label="图标" prop="icon">
              <div class="mIconBox">
                <el-popover placement="bottom" trigger="hover" v-model="mIconPopShow">
                  <div class="mIconPop">
                    <img src="/e6Images/m_1.png" data-src="/e6Images/m_1.png" @click="mChangeIcon">
                    <img src="/e6Images/m_2.png" data-src="/e6Images/m_2.png" @click="mChangeIcon">
                    <img src="/e6Images/m_3.png" data-src="/e6Images/m_3.png" @click="mChangeIcon">
                    <img src="/e6Images/m_4.png" data-src="/e6Images/m_4.png" @click="mChangeIcon">
                    <img src="/e6Images/m_5.png" data-src="/e6Images/m_5.png" @click="mChangeIcon">
                    <img src="/e6Images/m_6.png" data-src="/e6Images/m_6.png" @click="mChangeIcon">
                    <img src="/e6Images/m_7.png" data-src="/e6Images/m_7.png" @click="mChangeIcon">
                    <img src="/e6Images/m_8.png" data-src="/e6Images/m_8.png" @click="mChangeIcon">
                  </div>
                  <img :src="mParams.icon" slot="reference">
                </el-popover>
              </div>
            </el-form-item>
            <el-form-item label="面积" prop="acreage">
              <div class="horizontal">
                <el-input size="small" v-model="mParams.acreage" class="colorCol">
                  <template slot="append">k㎡</template>
                </el-input>
                <el-color-picker
                  v-model="mParams.acreageColor"
                  size="small"
                  class="colorPicker"
                  @change="mChangeColor"
                ></el-color-picker>
              </div>
            </el-form-item>
            <el-form-item label="名称" prop="name">
              <el-input size="small" v-model="mParams.name"></el-input>
            </el-form-item>
            <!--<el-form-item label="类型" prop="categoryId">
              <el-select v-model="mParams.categoryId" placeholder="请选择" size="small">
                <el-option
                  v-for="item in categoryList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>-->
            <el-form-item label="自编号" prop="code">
              <el-input size="small" v-model="mParams.code"></el-input>
            </el-form-item>
            <!--<el-form-item label="所属部门" prop="orgId">
              <e6-select
                      :dataList="organizations"
                      v-on:multipleSelect="mSelectOrg"
                      :filterable="true"
                      :slotTemplate="'tree'"
                      :placeholder="'请选择'"
                      widthData="165px"
                      ref="orgSelect_marker"
              ></e6-select>
            </el-form-item>
            <el-form-item label="共享模式" prop="share">
              <el-select v-model="mParams.share" placeholder="请选择" size="small">
                <el-option
                        v-for="item in shareList"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>-->
            <el-form-item label="半径" prop="radius">
              <el-select
                v-model="mParams.radius"
                placeholder="请选择"
                size="small"
                @change="mChangeRadius"
              >
                <el-option
                  v-for="item in mRadiusList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="经度" prop="lng84">
              <el-input size="small" v-model.number="mParams.lng84"></el-input>
            </el-form-item>
            <el-form-item label="纬度" prop="lat84">
              <el-input size="small" v-model.number="mParams.lat84"></el-input>
            </el-form-item>
            <el-form-item label="位置" prop="address" class="wholeRow">
              <el-input size="small" v-model="mParams.address"></el-input>
            </el-form-item>
            <el-form-item label="备注" prop="remark" class="wholeRow">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                v-model="mParams.remark"
              ></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div class="btnBox">
          <el-button type="primary" size="small" class="btn" @click="mSaveClick">确定</el-button>
          <el-button type="primary" size="small" class="btn" @click="mRedrawClick">重绘</el-button>
          <el-button size="small" class="btn" @click="mCloseLayer">取消</el-button>
        </div>
      </e6-box>
      <!-- 添加area详情层 -->
      <e6-box
        class="editLayer"
        ref="areaLayer"
        :title="operType == 1?'修改区域':'添加区域'"
        @closeFun="aCloseLayer"
      >
        <div class="tContent">
          <el-form
            :model="aParams"
            :rules="rules"
            ref="form_area"
            label-width="80px"
            class="formLayer"
          >
            <el-form-item label="名称" prop="name">
              <el-input size="small" v-model="aParams.name"></el-input>
            </el-form-item>
            <el-form-item label="面积" prop="acreage">
              <div class="horizontal">
                <el-input size="small" v-model="aParams.acreage" class="colorCol">
                  <template slot="append">k㎡</template>
                </el-input>
                <el-color-picker
                  v-model="aParams.acreageColor"
                  size="small"
                  class="colorPicker"
                  @change="aChangeColor"
                ></el-color-picker>
              </div>
            </el-form-item>
            <el-form-item label="自编号" prop="code">
              <el-input size="small" v-model="aParams.code"></el-input>
            </el-form-item>
            <!--<el-form-item label="类型" prop="categoryId">
              <el-select v-model="mParams.categoryId" placeholder="请选择" size="small">
                <el-option
                        v-for="item in categoryList"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="共享模式" prop="share">
              <el-select v-model="aParams.share" placeholder="请选择" size="small">
                <el-option
                        v-for="item in shareList"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="所属部门" prop="orgId">
              <e6-select
                      :dataList="organizations"
                      v-on:multipleSelect="aSelectOrg"
                      :filterable="true"
                      :slotTemplate="'tree'"
                      :placeholder="'请选择'"
                      widthData="165px"
                      ref="orgSelect_area"
              ></e6-select>
            </el-form-item>-->
            <el-form-item label="经度" prop="lng84">
              <el-input size="small" v-model.number="aParams.lng84"></el-input>
            </el-form-item>
            <el-form-item label="纬度" prop="lat84">
              <el-input size="small" v-model.number="aParams.lat84"></el-input>
            </el-form-item>
            <el-form-item label="位置" prop="address" class="wholeRow">
              <el-input size="small" v-model="aParams.address"></el-input>
            </el-form-item>
            <el-form-item label="备注" prop="remark" class="wholeRow">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                v-model="aParams.remark"
              ></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div class="btnBox">
          <el-button type="primary" size="small" class="btn" @click="aSaveClick">确定</el-button>
          <el-button type="primary" size="small" class="btn" @click="aRedrawClick">重绘</el-button>
          <el-button size="small" class="btn" @click="aCloseLayer">取消</el-button>
        </div>
      </e6-box>
      <!-- 添加line详情层 -->
      <e6-box
        class="editLayer"
        ref="lineLayer"
        :title="operType == 1?'修改线':'添加线'"
        @closeFun="lCloseLayer"
      >
        <div class="tContent">
          <el-form
            :model="lParams"
            :rules="rules_line"
            ref="form_line"
            label-width="80px"
            class="formLayer"
          >
            <el-form-item label="线宽度" prop="width" class="lineStyleBox">
              <el-popover
                placement="bottom"
                trigger="click"
                v-model="lineWidthPopShow"
                popper-class="lineWidthPop"
              >
                <ul>
                  <li
                    :style="{height:'4px',background:lParams.lineColor}"
                    @click="lineChangeWidth(4);"
                  ></li>
                  <li
                    :style="{height:'6px',background:lParams.lineColor}"
                    @click="lineChangeWidth(6);"
                  ></li>
                  <li
                    :style="{height:'8px',background:lParams.lineColor}"
                    @click="lineChangeWidth(8);"
                  ></li>
                  <li
                    :style="{height:'10px',background:lParams.lineColor}"
                    @click="lineChangeWidth(10);"
                  ></li>
                </ul>
                <div class="selectLineWidth" slot="reference">
                  <span :style="{height:lParams.width+'px',background:lParams.lineColor}"></span>
                </div>
              </el-popover>
              <el-color-picker
                v-model="lParams.lineColor"
                size="small"
                class="colorPicker"
                @change="lChangeColor"
              ></el-color-picker>
            </el-form-item>
            <el-form-item label="里程" prop="odometer">
              <div class="horizontal">
                <el-input size="small" v-model="lParams.odometer">
                  <template slot="append">km</template>
                </el-input>
              </div>
            </el-form-item>
            <el-form-item label="名称" prop="name">
              <el-input size="small" v-model="lParams.name"></el-input>
            </el-form-item>
            <el-form-item label="共享模式" prop="share">
              <el-select v-model="lParams.share" placeholder="请选择" size="small">
                <el-option
                  v-for="item in shareList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="自编号" prop="code">
              <el-input size="small" v-model="lParams.code"></el-input>
            </el-form-item>
            <el-form-item label="所属部门" prop="orgId">
              <e6-select
                :dataList="organizations"
                v-on:multipleSelect="lSelectOrg"
                :filterable="true"
                :slotTemplate="'tree'"
                :placeholder="'请选择'"
                widthData="165px"
                ref="orgSelect_line"
              ></e6-select>
            </el-form-item>
            <el-form-item label="开始位置" prop="bposition" class="wholeRow">
              <el-input size="small" v-model="lParams.bposition"></el-input>
            </el-form-item>
            <el-form-item label="结束位置" prop="eposition" class="wholeRow">
              <el-input size="small" v-model="lParams.eposition"></el-input>
            </el-form-item>
            <el-form-item label="备注" prop="remark" class="wholeRow">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                v-model="lParams.remark"
              ></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div class="btnBox">
          <el-button type="primary" size="small" class="btn" @click="lSaveClick">确定</el-button>
          <el-button size="small" class="btn" @click="lCloseLayer">取消</el-button>
        </div>
      </e6-box>
      <!-- 导航面板 -->
      <e6-box
        class="editLayer navigation"
        ref="navigationLayer"
        title="导航"
        @closeFun="closeLayer_navigation"
      >
        <div class="tContent">
          <el-form :model="navigation" ref="form_navigation" label-width="80px" class="formLayer">
            <el-form-item label="起点">
              <div class="horizontal">
                <el-select
                  v-model="navigation.start.id"
                  filterable
                  placeholder="请选择"
                  size="small"
                  @change="id=>{selectChange_navigation('start', id);}"
                >
                  <el-option
                    v-for="item in navigationDatas"
                    :key="'start_'+item.id + item.name"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
                <img
                  src="/e6Images/start.png"
                  title="在地图上标注起点"
                  @click="markMarker_navigation('start')"
                >
              </div>
            </el-form-item>
            <div class="midBox" v-show="navigation.midList.length>0">
              <vuescroll ref="midScroll_navigation" :ops="scrollOpts">
                <el-form-item
                  :label="'途径点'+(index+1)"
                  prop="end"
                  v-for="(item, index) in navigation.midList"
                  :key="index"
                  :id="'mid_'+index"
                >
                  <div class="horizontal">
                    <el-select
                      v-model="item.id"
                      filterable
                      placeholder="请选择"
                      size="small"
                      @change="id=>{selectChange_navigation(index, id);}"
                    >
                      <el-option
                        v-for="item in navigationDatas"
                        :key="'mid_'+item.id+item.name"
                        :label="item.name"
                        :value="item.id"
                      ></el-option>
                    </el-select>
                    <img
                      :src="midUrl_navigation(index)"
                      title="在地图上标注途径点"
                      @click="markMarker_navigation(index)"
                    >
                    <i
                      class="e6-icon-delete_line"
                      title="删除此途径点"
                      @click="deleteMid_navigation(index)"
                    ></i>
                  </div>
                </el-form-item>
              </vuescroll>
            </div>
            <el-form-item>
              <el-badge
                :value="navigation.midList.length"
                class="item"
                :hidden="navigation.midList.length==0"
              >
                <el-button
                  type="primary"
                  size="small"
                  :disabled="navigation.midList.length>=15"
                  :title="navigation.midList.length>=15?'最多只能添加15个途径点':''"
                  @click="addMid_navigation"
                >添加途径点</el-button>
              </el-badge>
            </el-form-item>
            <el-form-item label="终点">
              <div class="horizontal">
                <el-select
                  v-model="navigation.end.id"
                  filterable
                  placeholder="请选择"
                  size="small"
                  @change="value=>{selectChange_navigation('end', value);}"
                >
                  >
                  <el-option
                    v-for="item in navigationDatas"
                    :key="'end_'+item.id+item.name"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
                <img src="/e6Images/end.png" title="在地图上标注终点" @click="markMarker_navigation('end')">
              </div>
            </el-form-item>
            <el-form-item label="导航策略">
              <el-select v-model="navigation.policy" placeholder="请选择" size="small">
                <el-option label="最快捷模式" value="LEAST_TIME"></el-option>
                <el-option label="最经济模式" value="LEAST_FEE"></el-option>
                <el-option label="最短距离模式" value="LEAST_DISTANCE"></el-option>
                <el-option label="考虑实时路况" value="REAL_TRAFFIC"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </div>
        <div class="btnBox">
          <el-button type="primary" size="small" class="btn" @click="navigationClick">导航</el-button>
          <el-button size="small" class="btn" @click="closeLayer_navigation">取消</el-button>
        </div>
      </e6-box>
      <!-- 导航结果面板 -->
      <e6-box
        class="editLayer navigation"
        ref="resultLayer_navigation"
        title="导航结果"
        @closeFun="closeResultLayer_navigation"
      >
        <div class="navigationResultBox">
          <div class="summary">
            <div class="point">
              <img src="/e6Images/start.png">
              <p :title="navigation.start.address">{{navigation.start.address}}</p>
            </div>
            <div class="odo">
              <img src="/e6Images/arrow.png">
              <div class="text">
                <p>总里程</p>
                <p>{{navigation.odometer}} km</p>
              </div>
              <img src="/e6Images/arrow.png">
            </div>
            <div class="point">
              <img src="/e6Images/end.png">
              <p :title="navigation.end.address">{{navigation.end.address}}</p>
            </div>
          </div>
          <div class="subLine">
            <scrollbar>
              <div
                class="oneSub"
                v-for="(item, index) in navigation.result"
                :key="index"
                @click="showSubLine_navigation(index)"
                @mouseenter="mEnterSubLine_navigation(index)"
                @mouseleave="mLeaveSubLine_navigation(index)"
              >
                <h3>{{index + 1}}</h3>
                <p>{{item.instruction}}</p>
              </div>
            </scrollbar>
          </div>
        </div>
        <div class="btnBox">
          <el-button type="primary" size="small" class="btn" @click="saveLine_navigation">保存线路</el-button>
          <el-button type="primary" size="small" class="btn" @click="afresh_navigation">重新导航</el-button>
          <el-button size="small" class="btn" @click="closeResultLayer_navigation">关闭</el-button>
        </div>
      </e6-box>
    </div>
  </div>
</template>
<script>
  import "@/assets/AFont/iconfont.css";
  import { lazyAMapApiLoaderInstance } from "vue-amap";
  import { GaodeAPI } from "@/utils/map/gaodeAPI.js";
  import e6Box from "@/components/e6Box";
  import e6Select from "e6select";
  import { Scrollbar  } from "element-ui";
  import { mapGetters, mapActions } from "vuex";
  import {
    getCenterInfo,
    getNewLLByOld,
    ll02To84,
    getAcreage
  } from "@/utils/map/mapToolsFun.js";
  import vuescroll from 'vuescroll';
  export default {
    name: "e6LogisticsMap",
    components: {
      e6Box,
      e6Select,
      Scrollbar,
      vuescroll
    },
    props: {
      center: {
        type: Array,
        default: function() {
          return [113.57373, 22.29112];
        }
      },
      categoryList: {
        type: Array,
        default: function() {
          return [
            { value: 1, label: "工厂" },
            { value: 2, label: "收费站" },
            { value: 3, label: "仓库" },
            { value: 4, label: "码头堆场" },
            { value: 5, label: "物流园" },
            { value: 6, label: "其他" },
            { value: 7, label: "跟踪点" },
            { value: 8, label: "网点" }
          ];
        }
      },
      shareList: {
        type: Array,
        default: function() {
          return [
            { value: 1, label: "全局" },
            { value: 4, label: "个人" },
            { value: 2, label: "部门" }
          ];
        }
      },
      mRadiusList: {
        type: Array,
        default: function() {
          return [
            { value: 50, label: "50米" },
            { value: 100, label: "100米" },
            { value: 200, label: "200米" },
            { value: 500, label: "500米" },
            { value: 1000, label: "1000米" }
          ];
        }
      },
      mLevel: {
        type: String,
        default: "市"
      },
      mEyeShow: {
        type: Boolean,
        default: true
      },
      aLevel: {
        type: String,
        default: "市"
      },
      aEyeShow: {
        type: Boolean,
        default: false
      },
      isFullScreen: {
        type: Boolean,
        default: false
      }
    },
    computed: {
      ...mapGetters(["organizations", "navigationDatas"])
    },
    data() {
      // var fValidateName = (rule, value, callback) => {
      //   this.validateFenceName({
      //     rule,
      //     value,
      //     callback,
      //     params: this.fenceType == 1 ? this.mParams : this.aParams
      //   });
      // };
      // var lValidateName = (rule, value, callback) => {
      //   this.validateLineName({
      //     rule,
      //     value,
      //     callback,
      //     params: this.lParams
      //   });
      // };
      var orgId_validator = (rule, value, callback)=>{
        if(value == 0){
          callback(new Error('所属部门不能为空'));
        }
        else{
          callback();
        }
      };
      return {
        INTELLIGENT_NUM: 8,
        map: null,
        mapAPI: null,
        mapType: "tileLayer",
        logisMarkers: [], //物流marker
        logisAreas_centerMarker: [], //物流area的中心点marker
        logisAreas: [], //物流area
        logisCameras: [], //物流camera
        logisLines: [], //物流line
        marker: null,
        circle: null,
        area: null,
        line: null,
        geocoder: null, //geo对象（地址解析和逆地址解析）
        logisShow: false,
        markMarkerShow: false,
        markTypeLayerShow: false,
        operType: 0, // 0 - 添加；1 - 修改
        fenceType: 0, // 电子围栏类型，用于重绘共享相关不变数据。1 - 点；2 - 区域；
        // marker相关
        // markerLayerShow: false,
        mLevelPopShow: false,
        mIconPopShow: false,
        mShown: false, //marker是否已经绘制在地图上（因为marker有级别显示，所以mEyeShow不足以表示marker目前是否都已经绘制到了地图上。）
        mParams: {
          id: 0,
          icon: "/e6Images/m_1.png",
          width: 28,
          height: 28,
          name: "",
          nameColor: "#000",
          acreage: 0,
          acreageColor: "#409eff",
          categoryId: 0,
          code: "",
          orgId: 0,
          share: 1,
          radius: 200,
          lng84: 0,
          lat84: 0,
          lng02: 0,
          lat02: 0,
          address: "",
          remark: ""
        },
        // area相关
        // areaLayerShow: false,
        aLevelPopShow: false,
        aShown: false, //area是否已经绘制在地图上
        aParams: {
          id: 0,
          name: "",
          nameColor: "#000",
          acreage: 0,
          acreageColor: "#409eff",
          categoryId: 0,
          code: "",
          orgId: 0,
          share: 1,
          lng84: 0,
          lat84: 0,
          lng02: 0,
          lat02: 0,
          address: "",
          remark: "",
          llObjArr: [],
          typeId: 1 //图形类型  1 - 矩形, 2 - 多边形
        },
        // line相关
        // lineLayerShow: false,
        lineWidthPopShow: false,
        lParams: {
          id: 0,
          name: "",
          code: "",
          width: 4,
          orgId: 0,
          share: 1,
          odometer: 0,
          lineColor: "#409eff",
          bposition: "",
          eposition: "",
          remark: "",
          llObjArr: []
        },
        // 校验
        rules: {
          // name: [{ required: true, validator: fValidateName, trigger: "blur" }],
          name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
          lng84: [
            { required: true, message: "经度不能为空", trigger: "blur" },
            { type: "number", message: "经度必须为数字值", trigger: "blur" }
          ],
          lat84: [
            { required: true, message: "纬度不能为空", trigger: "blur" },
            { type: "number", message: "纬度必须为数字值", trigger: "blur" }
          ],
          orgId:[{ required: true, validator: orgId_validator, trigger: "blur" }]
        },
        rules_line: {
          // name: [{ required: true, validator: lValidateName, trigger: "blur" }]
          name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
          orgId: [{ required: true, validator: orgId_validator, trigger: "blur" }]
        },
        // 智能搜索
        intelligent: {
          isPanelShow: false,
          searchTxt: "",
          marker: null,
          moreMarkers: [], //点开更多的全部小图标的markers
          moreList: [],
          moreType: "",
          result: {}
        },
        // 路况
        isTrafficShow: false,
        // 拉框查询
        pullRect: {
          type: 0,
          title: "",
          maxMin: {},
          markers: [],
          marker: null,
          rect: null,
          datas: []
        },
        // 导航
        navigation: {
          start: {
            value: "",
            marker: null,
            address: ""
          },
          end: {
            value: "",
            marker: null,
            address: ""
          },
          midList: [],
          policy: "LEAST_TIME", // 导航策略
          result: [],
          odometer: 0,
          subLine: null // click 或 hover到subLine上绘制的线对象
        },
        scrollOpts:{
          bar:{
            background: "#909399",
            opacity: 0.3
          }
        }
      };
    },
    watch: {
      mLevel: {
        handler: function() {
          if (this.mapAPI != null){
            this.mShowOrHideByLevel();
          }
        }
      },
      mEyeShow: {
        handler: function() {
          if (this.mapAPI != null){
            this.mShowOrHideByEye();
          }
        }
      },
      aLevel: {
        handler: function() {
          if (this.mapAPI != null){
            this.aShowOrHideByLevel();
          }
        }
      },
      aEyeShow: {
        handler: function() {
          if (this.mapAPI != null){
            this.aShowOrHideByEye();
          }
        }
      },
      // lEyeShow: {
      //   handler: function() {
      //     this.lShowOrHideByEye();
      //   }
      // },
      categoryList: {
        handler: function() {
          if (this.mParams.categoryId == 0 && this.categoryList.length > 0) {
            this.mParams.categoryId = this.categoryList[0].value;
          }
          if (this.aParams.categoryId == 0 && this.categoryList.length > 0) {
            this.aParams.categoryId = this.categoryList[0].value;
          }
        },
        immediate: true
      }
    },
    methods: {
      ...mapActions(["setOrganizationAct"]),
      // 创建marker
      createMarkers(mDatasArr) {
        let markers = [];
        if (mDatasArr.length > 0) {
          mDatasArr.forEach(element => {
            let opts = Object.assign(
              {
                width: 28,
                height: 28,
                icon: "/e6Images/m_1.png",
                label: "",
                extData: {
                  id: -1,
                  setView: false,
                  clickback: null
                }
              },
              element
            );
            let lnglat = this.mapAPI.createLngLat(opts.llObj);
            let marker = this.mapAPI.createMarker({
              position: lnglat,
              ...opts
            });
            marker.id = opts.extData.id || -1;
            markers.push(marker);
            if (opts.extData.clickback != null) {
              this.mapAPI.addListener_click(marker, opts.extData.clickback);
            }
          });
        }
        return markers;
      },
      // 创建area
      createAreas(aDatasArr) {
        let areas = [];
        if (aDatasArr.length > 0) {
          aDatasArr.forEach(element => {
            let opts = Object.assign(
              {
                extData: {
                  id: -1,
                  setView: false
                }
              },
              element
            );
            let path = [];
            opts.llObjArr.forEach(llObj => {
              path.push(this.mapAPI.createLngLat(llObj));
            });
            let area = this.mapAPI.createPolygon({
              path,
              ...opts
            });
            area.id = opts.extData.id || -1;
            areas.push(area);
          });
        }
        return areas;
      },
      createAreas_center(aDatasArr) {
        let texts = [];
        if (aDatasArr.length > 0) {
          aDatasArr.forEach(element => {
            let opts = Object.assign(
              {
                extData: {
                  id: -1,
                  clickback: null
                }
              },
              element
            );
            let lnglat = this.mapAPI.createLngLat(opts.llObj);
            let text = this.mapAPI.createText({
              position: lnglat,
              ...opts
            });
            text.id = opts.extData.id || -1;
            texts.push(text);
            if (opts.extData.clickback != null) {
              this.mapAPI.addListener_click(text, opts.extData.clickback);
            }
          });
        }
        return texts;
      },
      // 创建line
      createLines(lDatasArr) {
        let lines = [];
        if (lDatasArr.length > 0) {
          lDatasArr.forEach(element => {
            let opts = Object.assign(
              {
                extData: {
                  id: -1,
                  setView: false
                }
              },
              element
            );
            let path = [];
            opts.llObjArr.forEach(llObj => {
              path.push(this.mapAPI.createLngLat(llObj));
            });
            let line = this.mapAPI.createLine({
              path,
              ...opts
            });
            line.id = opts.extData.id || -1;
            lines.push(line);
          });
        }
        return lines;
      },

      // 标注overlay
      markOverlay(type) {
        this.logisShow = false;
        this.markTypeLayerShow = false;
        let rOpts = {};
        switch (type) {
          case "marker":
            rOpts = {
              type: "marker",
              callback: this.markback_marker,
              opts: {
                draggable: true
              }
            };
            break;
          case "rectangle":
            rOpts = {
              type: "rectangle",
              callback: this.markback_rect
            };
            break;
          case "polygon":
            rOpts = {
              type: "polygon",
              callback: this.markback_polygon
            };
            break;
        }
        this.mapAPI.drawOverlay(rOpts);
      },
      // 打开标注类型选择层
      openMarkTypeLayer(hasMarker = false) {
        this.markMarkerShow = hasMarker;
        this.markTypeLayerShow = true;
      },
      // 关闭标注类型选择层
      closeMarkTypeLayer() {
        this.markTypeLayerShow = false;
      },
      // 根据id删除marker或者area
      deleteLogisOverlays(idsArr) {
        this.deleteLogisMarkers(idsArr);
        this.deleteLogisAreas(idsArr);
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      //----------------------------------marker------------------------------//
      // 删除多个物流marker
      deleteLogisMarkers(mIdsArr = []) {
        for (let i = this.logisMarkers.length - 1; i >= 0; i--) {
          let marker = this.logisMarkers[i];
          if (mIdsArr.length == 0 || mIdsArr.indexOf(marker.id) > -1) {
            this.mapAPI.removeOverlay(marker);
            this.logisMarkers.splice(i, 1);
          }
        }
      },
      // 增加多个物流marker
      addLogisMarkers(mDatasArr) {
        let markers = this.createMarkers(mDatasArr);
        this.mShowOrHideByEye(markers);
        this.logisMarkers = this.logisMarkers.concat(markers);
      },
      //根据“全国省市区”情况加载点
      mShowOrHideByLevel() {
        let currentZoom = this.mapAPI.getZoom();
        let displayZoom = this.mapAPI.getZoomByLevelTxt(this.mLevel);
        if (this.mEyeShow) {
          if (currentZoom >= displayZoom && !this.mShown) {
            this.mapAPI.addOverlay(this.logisMarkers);
            this.mShown = true;
          } else if (currentZoom < displayZoom && this.mShown) {
            this.mapAPI.removeOverlay(this.logisMarkers);
            this.mShown = false;
            this.mapAPI.closeInfoWindow();
          }
        }
      },
      //根据“eye”情况加载点
      mShowOrHideByEye(markersArr) {
        let markers = this.logisMarkers;
        if (typeof markersArr != "undefined" && markersArr != null) {
          markers = markersArr;
        }
        if (this.mEyeShow) {
          let currentZoom = this.mapAPI.getZoom();
          let displayZoom = this.mapAPI.getZoomByLevelTxt(this.mLevel);
          if (currentZoom >= displayZoom) {
            this.mapAPI.addOverlay(markers);
            this.mShown = true;
          }
        } else {
          if (this.mShown) {
            this.mapAPI.removeOverlay(markers);
            this.mShown = false;
            this.mapAPI.closeInfoWindow();
          }
        }
      },
      //标注marker回调
      markback_marker(overlay) {
        this.marker = overlay;
        //处理lnglat和address
        let oLngLat = this.mapAPI.getPosition(overlay);
        this.mGetAddress(oLngLat.lnglat);
        this.mParams.lng02 = oLngLat.lng;
        this.mParams.lat02 = oLngLat.lat;
        this.mLnglatConvert(true);
        this.operType = 0;
        this.mAddDragListener();
        //绘制circle
        this.mDrawCircle(oLngLat.lnglat);
        // 计算面积
        this.mParams.acreage = (
          (Math.PI * Math.pow(this.mParams.radius, 2)) /
          Math.pow(1000, 2)
        ).toFixed(3);
        //重绘时同步原有信息
        if (this.fenceType != 0 && this.fenceType != 1) {
          let params = {};
          if (this.fenceType == 2) {
            params = this.aParams;
          }
          this.mParams.id = params.id;
          this.mParams.name = params.name;
          this.mParams.nameColor = params.nameColor;
          this.mParams.acreageColor = params.acreageColor;
          this.mParams.categoryId = params.categoryId;
          this.mParams.code = params.code;
          this.mParams.orgId = params.orgId;
          this.mParams.share = params.share;
          this.mParams.address = params.address;
          this.mParams.remark = params.remark;
        }
        this.fenceType = 1;
        this.$refs.markerLayer.showBox();
      },
      // 监听marker的drag的事件
      mAddDragListener() {
        this.mapAPI.addListener_drag(this.marker, rObj => {
          this.mParams.lng02 = rObj.lng;
          this.mParams.lat02 = rObj.lat;
          this.mLnglatConvert(true);
          this.mGetAddress(this.mapAPI.getPosition(this.marker).lnglat);
          if (this.circle != null) {
            this.mapAPI.setPosition(
              this.circle,
              this.mapAPI.getPosition(this.marker).lnglat
            );
          }
        });
      },
      mLnglatConvert(is02To84) {
        if (is02To84) {
          let llObj_84 = ll02To84(this.mParams.lng02, this.mParams.lat02);
          this.mParams.lng84 = +llObj_84.lng.toFixed(6);
          this.mParams.lat84 = +llObj_84.lat.toFixed(6);
        } else {
          let llObj_02 = ll02To84(this.mParams.lng84, this.mParams.lat84);
          this.mParams.lng02 = +llObj_02.lng.toFixed(6);
          this.mParams.lat02 = +llObj_02.lat.toFixed(6);
        }
      },
      // 绘制circle
      mDrawCircle(lnglat) {
        this.circle = this.mapAPI.createCircle({
          center: lnglat,
          radius: this.mParams.radius,
          strokeColor: this.mParams.acreageColor,
          fillColor: this.mParams.acreageColor
        });
        this.mapAPI.addOverlay(this.circle);
      },
      //获取marker的地址
      mGetAddress(lnglat) {
        this.mapAPI.getAddress(lnglat, result => {
          if (result != null) {
            this.mParams.address = result.formattedAddress;
          }
        });
      },
      // marker省市区修改事件
      mLevelChange(level) {
        this.mLevelPopShow = false;
        this.$emit("update:mLevel", level);
        this.$emit("levelChange", "marker", level);
      },
      // marker的eye控制显隐
      mEyeClick() {
        let IsShow = !this.mEyeShow;
        this.$emit("update:mEyeShow", IsShow);
        this.$emit("eyeChange", "marker", IsShow);
      },
      //修改marker触发事件
      mOpenLayer(opts) {
        console.log('opts :', opts);
        this.mapAPI.removeOverlay([this.marker, this.circle]);
        Object.assign(this.mParams, opts);
        if (this.mParams.id != 0) {
          this.operType = 1;
        }
        this.fenceType = 1;
        this.$refs.orgSelect_marker.setSelectList([this.mParams.orgId]);
        this.$refs.markerLayer.showBox(); //打开marker面板
        //marker
        let lnglat = this.mapAPI.createLngLat({
          lng84: this.mParams.lng84,
          lat84: this.mParams.lat84,
          lng02: this.mParams.lng02,
          lat02: this.mParams.lat02
        });
        this.marker = this.mapAPI.createMarker({
          position: lnglat,
          color: this.mParams.nameColor,
          draggable: true,
          icon: this.mParams.icon,
          width: this.mParams.width,
          height: this.mParams.height
        });
        this.mapAPI.addOverlay(this.marker);
        this.mAddDragListener();
        this.mDrawCircle(lnglat);
        this.mapAPI.setCenter(lnglat);
        this.mapAPI.setzIndex({ marker: this.marker, zIndex: 500 });
        // // 计算面积
        // this.mParams.acreage = (
        //   (Math.PI * Math.pow(this.mParams.radius, 2)) /
        //   Math.pow(1000, 2)
        // ).toFixed(3);
        //将line放在可视范围之内
        this.mapAPI.setFitView({ overlayList: [this.marker] });
      },
      //marker面板保存
      // operType: 0 - 添加；1 - 修改
      mSaveClick() {
        this.$refs.form_marker.validate(valid => {
          if (valid) {
            this.$emit("saveOverlay", {
              type: "marker",
              operType: this.operType,
              params: this.mParams
            });
          } else {
            return false;
          }
        });
      },
      // 关闭marker面板
      mCloseLayer() {
        this.$refs.markerLayer.closeBox(); // 组件中已经处理，主动调此方法不会再触发回调，防止死循环。
        this.$refs.orgSelect_marker.multipleRemoveAll();
        this.operType = 0;
        this.fenceType = 0;
        this.mapAPI.removeOverlay([this.marker, this.circle]);
        this.marker = null;
        this.circle = null;
        this.mParams = {
          id: 0,
          icon: "/e6Images/m_1.png",
          width: 28,
          height: 28,
          name: "",
          nameColor: "#000",
          acreage: 0,
          acreageColor: "#409eff",
          categoryId: this.categoryList[0].value || 0,
          code: "",
          orgId: 0,
          share: 1,
          radius: 200,
          lng84: 0,
          lat84: 0,
          lng02: 0,
          lat02: 0,
          address: "",
          remark: ""
        };
        this.resetForm('form_marker');
      },
      //重绘
      mRedrawClick() {
        this.$refs.markerLayer.closeBox(); //打开marker面板
        this.mapAPI.removeOverlay([this.marker, this.circle, this.area]);
        this.marker = null;
        this.circle = null;
        this.area = null;
        this.openMarkTypeLayer(true);
      },
      // marker切换图标
      mChangeIcon() {
        this.mParams.icon = event.srcElement.dataset.src;
        if (this.marker != null) {
          this.mapAPI.setIcon(this.marker, { icon: this.mParams.icon });
        }
      },
      // marker切换半径
      mChangeRadius() {
        if (this.marker != null) {
          this.mParams.acreage = (
            (Math.PI * Math.pow(this.mParams.radius, 2)) /
            Math.pow(1000, 2)
          ).toFixed(3);
          this.mapAPI.setOptions(this.circle, {
            radius: this.mParams.radius
          });
        }
      },
      // marker切换面积颜色
      mChangeColor() {
        if (this.marker != null) {
          this.mapAPI.setOptions(this.circle, {
            strokeColor: this.mParams.acreageColor,
            fillColor: this.mParams.acreageColor
          });
        }
      },
      mSelectOrg(selArr) {
        if (selArr.length == 0) {
          this.mParams.orgId = 0;
        } else {
          this.mParams.orgId = selArr[0].id;
        }
      },
      //------------------------------------area--------------------------------//
      deleteLogisAreas(aIdsArr = []) {
        for (let i = this.logisAreas.length - 1; i >= 0; i--) {
          let area = this.logisAreas[i];
          if (aIdsArr.length == 0 || aIdsArr.indexOf(area.id) > -1) {
            this.mapAPI.removeOverlay(area);
            this.logisAreas.splice(i, 1);
          }
        }
        // 删除中心点
        for (let i = this.logisAreas_centerMarker.length - 1; i >= 0; i--) {
          let centerMarker = this.logisAreas_centerMarker[i];
          if (aIdsArr.length == 0 || aIdsArr.indexOf(centerMarker.id) > -1) {
            this.mapAPI.removeOverlay(centerMarker);
            this.logisAreas_centerMarker.splice(i, 1);
          }
        }
      },
      addLogisAreas(aDatasArr) {
        let areas = this.createAreas(aDatasArr);
        this.logisAreas = this.logisAreas.concat(areas);
        //创建area的中心点
        let centerDatasArr = [];
        aDatasArr.forEach(aData => {
          let opts = {
            llObj: {
              lng84: aData.lng84 || 0,
              lat84: aData.lat84 || 0,
              lng02: aData.lng02 || 0,
              lat02: aData.lat02 || 0
            },
            icon: "/e6Images/blank.png",
            width: 2,
            height: 2,
            ...aData
          };
          centerDatasArr.push(opts);
        });
        let centerMarkers = this.createAreas_center(centerDatasArr);
        this.logisAreas_centerMarker = this.logisAreas_centerMarker.concat(
          centerMarkers
        );
        this.aShowOrHideByEye({
          areas,
          centerMarkers
        });
      },
      aShowOrHideByLevel() {
        let currentZoom = this.mapAPI.getZoom();
        let displayZoom = this.mapAPI.getZoomByLevelTxt(this.aLevel);
        if (this.aEyeShow) {
          if (currentZoom >= displayZoom && !this.aShown) {
            this.mapAPI.addOverlay([
              ...this.logisAreas,
              ...this.logisAreas_centerMarker
            ]);
            this.aShown = true;
          } else if (currentZoom < displayZoom && this.aShown) {
            this.mapAPI.removeOverlay([
              ...this.logisAreas,
              ...this.logisAreas_centerMarker
            ]);
            this.aShown = false;
            this.mapAPI.closeInfoWindow();
          }
        }
      },
      aShowOrHideByEye(opts) {
        let areas = this.logisAreas;
        let centerMarkers = this.logisAreas_centerMarker;
        if (typeof opts != "undefined") {
          if (typeof opts.areasArr != "undefined" && opts.areasArr != null) {
            areas = opts.areasArr;
          }
          if (
            typeof opts.centerMarkers != "undefined" &&
            opts.centerMarkers != null
          ) {
            centerMarkers = opts.centerMarkers;
          }
        }
        if (this.aEyeShow) {
          let currentZoom = this.mapAPI.getZoom();
          let displayZoom = this.mapAPI.getZoomByLevelTxt(this.aLevel);
          if (currentZoom >= displayZoom) {
            this.mapAPI.addOverlay(areas);
            this.mapAPI.addOverlay(centerMarkers);
            this.aShown = true;
          }
        } else {
          if (this.aShown) {
            this.mapAPI.removeOverlay(areas);
            this.mapAPI.removeOverlay(centerMarkers);
            this.aShown = false;
            this.mapAPI.closeInfoWindow();
          }
        }
      },
      markback_rect(overlay) {
        this.aParams.typeId = 1;
        this.markback_area(overlay);
      },
      markback_polygon(overlay) {
        this.aParams.typeId = 2;
        this.markback_area(overlay);
      },
      markback_area(overlay) {
        this.area = overlay;
        let path = this.mapAPI.getPath(overlay);
        let llObjArr_02 = [];
        let llObjArr = []; //存储到aParams中，保存使用
        let llArr_02 = []; //经纬度数组，计算面积使用
        path.forEach(lnglat => {
          let lng02 = this.mapAPI.getLng(lnglat);
          let lat02 = this.mapAPI.getLat(lnglat);
          llObjArr_02.push({ lng: lng02, lat: lat02 });
          llArr_02.push([lng02, lat02]);
          let llObj_84 = ll02To84(lng02, lat02);
          llObjArr.push({
            lng02,
            lat02,
            lng84: llObj_84.lng,
            lat84: llObj_84.lat
          });
        });
        this.aParams.llObjArr = llObjArr.concat();
        let centerInfo = getCenterInfo(llObjArr_02);
        let lnglat = this.mapAPI.createLngLat({
          lng02: centerInfo.lng_center,
          lat02: centerInfo.lat_center
        });
        this.marker = this.mapAPI.createMarker({
          position: lnglat,
          icon: "/e6Images/center.png",
          width: 28,
          height: 28,
          draggable: true
        });
        this.mapAPI.addOverlay(this.marker);
        this.aGetAddress(lnglat);
        this.aParams.lng02 = centerInfo.lng_center;
        this.aParams.lat02 = centerInfo.lat_center;
        this.aLnglatConvert(true);
        this.operType = 0;
        this.aAddDragListener();
        // 计算面积
        this.aParams.acreage = getAcreage(llArr_02);
        //重绘时同步原有信息
        if (this.fenceType != 0 && this.fenceType != 2) {
          let params = {};
          if (this.fenceType == 1) {
            params = this.mParams;
          }
          this.aParams.id = params.id;
          this.aParams.name = params.name;
          this.aParams.nameColor = params.nameColor;
          this.aParams.acreageColor = params.acreageColor;
          this.aParams.categoryId = params.categoryId;
          this.aParams.code = params.code;
          this.aParams.orgId = params.orgId;
          this.aParams.share = params.share;
          this.aParams.address = params.address;
          this.aParams.remark = params.remark;
        }
        this.fenceType = 2;
        // this.areaLayerShow = true;
        this.$refs.areaLayer.showBox();
      },
      aLnglatConvert(is02To84) {
        if (is02To84) {
          let llObj_84 = ll02To84(this.aParams.lng02, this.aParams.lat02);
          this.aParams.lng84 = +llObj_84.lng.toFixed(6);
          this.aParams.lat84 = +llObj_84.lat.toFixed(6);
        } else {
          let llObj_02 = ll02To84(this.aParams.lng84, this.aParams.lat84);
          this.aParams.lng02 = +llObj_02.lng.toFixed(6);
          this.aParams.lat02 = +llObj_02.lat.toFixed(6);
        }
      },
      aAddDragListener() {
        this.mapAPI.addListener_drag(this.marker, rObj => {
          let lng_old = this.aParams.lng02;
          let lat_old = this.aParams.lat02;
          let lng_new = rObj.lng;
          let lat_new = rObj.lat;
          this.aParams.lng02 = lng_new;
          this.aParams.lat02 = lat_new;
          this.aLnglatConvert(true);
          this.aGetAddress(this.mapAPI.getPosition(this.marker).lnglat);
          //处理area相关
          if (this.area != null) {
            let path_old = this.mapAPI.getPath(this.area);
            var llObjArr_old = [];
            path_old.forEach(lnglat => {
              let oLngLat = {
                lng: this.mapAPI.getLng(lnglat),
                lat: this.mapAPI.getLat(lnglat)
              };
              llObjArr_old.push(oLngLat);
            });
            let llObjArr_new = getNewLLByOld({
              lng_old,
              lat_old,
              lng_new,
              lat_new,
              llObjArr_old
            });
            let path_new = [];
            let llObjArr = [];
            llObjArr_new.forEach(llObj => {
              path_new.push(
                this.mapAPI.createLngLat({
                  lng02: llObj.lng,
                  lat02: llObj.lat
                })
              );
              let llObj_84 = ll02To84(llObj.lng, llObj.lat);
              llObjArr.push({
                lng02: llObj.lng,
                lat02: llObj.lat,
                lng84: llObj_84.lng,
                lat84: llObj_84.lat
              });
            });
            this.aParams.llObjArr = llObjArr.concat();
            this.mapAPI.removeOverlay(this.area);
            this.area = this.mapAPI.createPolygon({
              path: path_new,
              strokeColor: this.aParams.acreageColor,
              fillColor: this.aParams.acreageColor
            });
            this.mapAPI.addOverlay(this.area);
          }
        });
      },
      aGetAddress(lnglat) {
        this.mapAPI.getAddress(lnglat, result => {
          if (result != null) {
            this.aParams.address = result.formattedAddress;
          }
        });
      },
      aLevelChange(level) {
        this.aLevelPopShow = false;
        this.$emit("update:aLevel", level);
        this.$emit("levelChange", "area", level);
      },
      aEyeClick() {
        let IsShow = !this.aEyeShow;
        this.$emit("update:aEyeShow", IsShow);
        this.$emit("eyeChange", "area", IsShow);
      },
      aOpenLayer(opts) {
        this.mapAPI.removeOverlay([this.marker, this.area]);
        Object.assign(this.aParams, opts);
        if (this.aParams.id != 0) {
          this.operType = 1;
        }
        this.fenceType = 2;
        this.$refs.orgSelect_area.setSelectList([this.aParams.orgId]);
        this.$refs.areaLayer.showBox();
        //marker
        let lnglat = this.mapAPI.createLngLat({
          lng84: this.aParams.lng84,
          lat84: this.aParams.lat84,
          lng02: this.aParams.lng02,
          lat02: this.aParams.lat02
        });
        this.aParams.lng02 = this.mapAPI.getLng(lnglat);
        this.aParams.lat02 = this.mapAPI.getLat(lnglat);
        this.marker = this.mapAPI.createMarker({
          position: lnglat,
          color: this.aParams.nameColor,
          draggable: true,
          icon: "/e6Images/center.png",
          width: 28,
          height: 28
        });
        this.mapAPI.addOverlay(this.marker);
        this.aAddDragListener();
        this.mapAPI.setCenter(lnglat);
        //area
        let path = [];
        this.aParams.llObjArr.forEach(llObj => {
          path.push(
            this.mapAPI.createLngLat({
              lng84: llObj.lng84 || 0,
              lat84: llObj.lat84 || 0,
              lng02: llObj.lng02 || 0,
              lat02: llObj.lat02 || 0
            })
          );
        });
        this.area = this.mapAPI.createPolygon({
          path: path,
          strokeColor: this.aParams.acreageColor,
          fillColor: this.aParams.acreageColor
        });
        this.mapAPI.addOverlay(this.area);
        //将area放在可视范围之内
        this.mapAPI.setFitView({ overlayList: [this.area] });
      },
      aSaveClick() {
        this.$refs.form_area.validate(valid => {
          if (valid) {
            this.$emit("saveOverlay", {
              type: "area",
              operType: this.operType,
              params: this.aParams
            });
          } else {
            return false;
          }
        });
      },
      aCloseLayer() {
        this.$refs.areaLayer.closeBox();
        this.$refs.orgSelect_area.multipleRemoveAll();
        this.operType = 0;
        this.fenceType = 0;
        this.mapAPI.removeOverlay([this.marker, this.area]);
        this.area = null;
        this.marker = null;
        this.aParams = {
          id: 0,
          name: "",
          nameColor: "#000",
          acreage: 0,
          acreageColor: "#409eff",
          categoryId: this.categoryList[0].value || 0,
          code: "",
          orgId: 0,
          share: 1,
          lng84: 0,
          lat84: 0,
          lng02: 0,
          lat02: 0,
          address: "",
          remark: "",
          llObjArr: [],
          typeId: 1
        };
        this.resetForm('form_area');
      },
      aRedrawClick() {
        this.$refs.areaLayer.closeBox();
        this.mapAPI.removeOverlay([this.marker, this.circle, this.area]);
        this.marker = null;
        this.circle = null;
        this.area = null;
        this.openMarkTypeLayer(true);
      },
      aChangeColor() {
        if (this.area != null) {
          this.mapAPI.setOptions(this.area, {
            strokeColor: this.aParams.acreageColor,
            fillColor: this.aParams.acreageColor
          });
        }
      },
      aSelectOrg(selArr) {
        if (selArr.length == 0) {
          this.aParams.orgId = 0;
        } else {
          this.aParams.orgId = selArr[0].id;
        }
      },
      //-------------------------------line-----------------------------//
      // //根据“eye”情况加载点
      // lShowOrHideByEye(linesArr) {
      //   let lines = this.logisLines;
      //   if (typeof linesArr != "undefined" && linesArr != null) {
      //     lines = linesArr;
      //   }
      //   if (this.lEyeShow) {
      //     this.mapAPI.addOverlay(lines);
      //   } else {
      //     this.mapAPI.removeOverlay(lines);
      //   }
      // },
      lChangeColor() {
        if (this.line != null) {
          this.mapAPI.setOptions(this.line, {
            strokeColor: this.lParams.lineColor
          });
        }
      },
      lCloseLayer() {
        this.$refs.lineLayer.closeBox();
        this.$refs.orgSelect_line.multipleRemoveAll();
        this.operType = 0;
        this.mapAPI.removeOverlay(this.line);
        this.line = null;
        this.lParams = {
          id: 0,
          name: "",
          code: "",
          width: 4,
          orgId: 0,
          share: 1,
          odometer: 0,
          lineColor: "#409eff",
          bposition: "",
          eposition: "",
          remark: "",
          llObjArr: []
        };
        this.resetForm('form_line');
      },
      lineChangeWidth(width) {
        this.lParams.width = width;
        this.mapAPI.setOptions(this.line,{strokeWeight: width});
      },
      lSaveClick() {
        this.$refs.form_line.validate(valid => {
          if (valid) {
            this.$emit("saveOverlay", {
              type: "line",
              operType: this.operType,
              params: this.lParams
            });
          } else {
            return false;
          }
        });
      },
      lOpenLayer(opts) {
        this.mapAPI.removeOverlay(this.line);
        Object.assign(this.lParams, opts);
        if (this.lParams.id != 0) {
          this.operType = 1;
        }
        this.$refs.orgSelect_line.setSelectList([this.lParams.orgId]);
        this.$refs.lineLayer.showBox();
        //line
        let path = [];
        this.lParams.llObjArr.forEach(llObj => {
          path.push(
            this.mapAPI.createLngLat({
              lng84: llObj.lng84 || 0,
              lat84: llObj.lat84 || 0,
              lng02: llObj.lng02 || 0,
              lat02: llObj.lat02 || 0
            })
          );
        });
        this.line = this.mapAPI.createLine({
          path: path,
          strokeColor: this.lParams.lineColor,
          strokeWeight: this.lParams.width,
          zIndex: 100
        });
        this.mapAPI.addOverlay(this.line);
        //将line放在可视范围之内
        this.mapAPI.setFitView({ overlayList: [this.line] });
      },
      // 删除多个物流marker
      deleteLogisLines(lIdsArr) {
        for (let i = this.logisLines.length - 1; i >= 0; i--) {
          let line = this.logisLines[i];
          if (lIdsArr.indexOf(line.id) > -1) {
            this.mapAPI.removeOverlay(line);
            this.logisLines.splice(i, 1);
          }
        }
      },
      // 增加多个物流line
      addLogisLines(lDatasArr) {
        let lines = this.createLines(lDatasArr);
        this.logisLines = this.logisLines.concat(lines);
      },
      lSelectOrg(selArr) {
        if (selArr.length == 0) {
          this.lParams.orgId = 0;
        } else {
          this.lParams.orgId = selArr[0].id;
        }
      },
      //--------------------------------智能搜索---------------------------------//
      changeAnother_intelligent(type) {
        let oData = this.intelligent.result[type] || null;
        if (oData != null) {
          if (oData.nextIndex > oData.datas.length - 1) {
            oData.nextIndex = 0;
          }
          let endIndex = oData.nextIndex + this.INTELLIGENT_NUM;
          if (endIndex > oData.datas.length - 1) {
            endIndex = oData.datas.length;
          }

          oData.aShow = oData.datas.slice(oData.nextIndex, endIndex);
          oData.nextIndex += this.INTELLIGENT_NUM;
          // 因为数据嵌套层次太深，不能触发render函数重新渲染，所以要手动调用强制更新函数重新渲染。
          this.$forceUpdate();
        }
      },
      showMore_intelligent(type) {
        this.clearMoreLayer_intelligent();
        this.intelligent.moreType = type;
        this.intelligent.moreList = this.intelligent.result[type].datas;
        this.intelligent.moreList.forEach(item => {
          let opts = {
            position: this.mapAPI.createLngLat({
              lng84: item.lng84,
              lat84: item.lat84
            }),
            width: 9,
            height: 13,
            icon: "/e6Images/marker_small.png",
            winDetails: item.winInfo,
            offsetType: 1 //0-图标整体中心；1-图标底部中心
          };
          let marker = this.mapAPI.createMarker(opts);
          this.mapAPI.addOverlay(marker);
          this.intelligent.moreMarkers.push(marker);
        });
        this.mapAPI.setFitView({ overlayList: this.intelligent.moreMarkers });
        this.$refs.moreLayer_intelligent.showBox();
      },
      closeMoreLayer_intelligent() {
        this.clearMoreLayer_intelligent();
        this.$refs.moreLayer_intelligent.closeBox();
      },
      clearMoreLayer_intelligent() {
        this.intelligent.moreType = "";
        this.intelligent.moreList = [];
        this.intelligent.moreMarkers.forEach(item => {
          this.mapAPI.removeOverlay(item);
        });
        this.intelligent.moreMarkers = [];
      },
      closePanel_intelligent() {
        if (this.intelligent.marker != null) {
          this.mapAPI.removeOverlay(this.intelligent.marker);
        }
        this.intelligent = {
          isPanelShow: false,
          searchTxt: "",
          marker: null,
          moreMarkers: [],
          moreList: [],
          moreType: "",
          result: {}
        };
        this.closeMoreLayer_intelligent();
      },
      search_intelligent() {
        if (this.intelligent.searchTxt != "") {
          // 查询关键字
          this.mapAPI.keywordsSearch(this.intelligent.searchTxt).then(res => {
            this.intelligent.result["keywords"] = {
              title: "关键字",
              nextIndex: this.INTELLIGENT_NUM,
              datas: res,
              aShow: res.slice(0, this.INTELLIGENT_NUM)
            };
            this.intelligent.isPanelShow = true;
            // 因为数据嵌套层次太深，不能触发render函数重新渲染，所以要手动调用强制更新函数重新渲染。
            this.$forceUpdate();
          });
          // 回调父组件查询vehicle和marker，area
          this.$emit("intelligentSearch", this.intelligent.searchTxt);
        } else {
          this.$message({
            message: "请先输入要搜索的内容",
            type: "warning"
          });
        }
      },
      searchBack_intelligent(result) {
        let keywords = this.intelligent.result["keywords"] || {};
        if (this.intelligent.result["keywords"]) {
          this.intelligent.result = {
            keywords
          };
        } else {
          this.intelligent.result = {};
        }
        for (let item in result) {
          let aShow = result[item].datas.slice(0, this.INTELLIGENT_NUM);
          this.intelligent.result[item] = {
            ...result[item],
            aShow,
            nextIndex: this.INTELLIGENT_NUM
          };
        }
        this.intelligent.isPanelShow = true;
      },
      showDetails_intelligent(type, id) {
        let oData = this.intelligent.result[type] || null;
        if (oData) {
          let data = oData.datas.find(item => {
            return item.id == id;
          });
          if (data) {
            if (this.intelligent.marker != null) {
              this.mapAPI.removeOverlay(this.intelligent.marker);
              this.intelligent.marker = null;
            }

            let opts = {
              position: this.mapAPI.createLngLat({
                lng84: data.lng84,
                lat84: data.lat84
              }),
              width: 33,
              height: 40,
              icon: "/e6Images/point.png",
              label: data.name,
              winDetails: data.winInfo,
              offsetType: 1 //0-图标整体中心；1-图标底部中心
            };
            this.intelligent.marker = this.mapAPI.createMarker(opts);
            this.mapAPI.addOverlay(this.intelligent.marker);
            this.mapAPI.setFitView({ overlayList: [this.intelligent.marker] });
          }
        }
      },
      // 测距
      openRangingTool() {
        this.mapAPI.openRangingTool();
      },
      // 全屏
      fullScreen() {
        this.$emit("fullScreen");
      },
      // 路况
      showTraffic() {
        this.mapAPI.showTraffic();
        this.isTrafficShow = true;
      },
      refreshTraffic() {
        this.mapAPI.refreshTraffic();
      },
      closeTraffic() {
        this.isTrafficShow = false;
        this.mapAPI.hideTraffic();
      },
      // 拉框查找
      drawRectToSearch_pullRect(type = 0, title = "") {
        this.clearDatas_pullRect();
        this.pullRect.type = type;
        this.pullRect.title = title;
        this.mapAPI.drawOverlay({
          type: "rectangle",
          callback: overlay => {
            this.drawRectBack_pullRect(overlay);
          }
        });
      },
      // type: 1 - 找自己的车；2 - 找已经定位的车
      drawRectBack_pullRect(overlay = null) {
        if (overlay) {
          this.pullRect.rect = overlay;
          // maxMin: { sw: { lng, lat }, ne: { lng, lat }}
          let maxMin = this.mapAPI.getMaxMin(overlay);
          this.pullRect.maxMin = maxMin;
          this.$emit("pullRectSearch", { type: this.pullRect.type, maxMin });
        }
      },
      // 拉框查找自己的车回调
      pullRectSearchBack({ type = 0, datas = [] }) {
        if(datas.length == 0){
          this.$message({
            message: "没有找到相关车辆",
            type: "warning"
          });
          this.clearDatas_pullRect();
          return;
        }
        this.pullRect.datas = datas;
        this.pullRect.datas.forEach(item => {
          let opts = {
            position: this.mapAPI.createLngLat({
              lng84: item.lng84,
              lat84: item.lat84
            }),
            width: 9,
            height: 13,
            icon: "/e6Images/marker_small.png",
            winDetails: item.winInfo,
            offsetType: 1 //0-图标整体中心；1-图标底部中心
          };
          let marker = this.mapAPI.createMarker(opts);
          this.mapAPI.addOverlay(marker);
          this.pullRect.markers.push(marker);
        });
        this.$refs.pullRectLayer.showBox();
      },
      clearDatas_pullRect() {
        if (this.pullRect.rect) {
          this.mapAPI.removeOverlay(this.pullRect.rect);
        }
        if (this.pullRect.marker != null) {
          this.mapAPI.removeOverlay(this.pullRect.marker);
        }
        this.pullRect.markers.forEach(item => {
          this.mapAPI.removeOverlay(item);
        });
        this.pullRect = {
          type: 0,
          title: "",
          maxMin: {},
          markers: [],
          marker: null,
          rect: null,
          datas: []
        };
      },
      closeListLayer_pullRect() {
        this.clearDatas_pullRect();
      },
      rowClick_pullRect(row) {
        if (row) {
          if (this.pullRect.marker != null) {
            this.mapAPI.removeOverlay(this.pullRect.marker);
            this.pullRect.marker = null;
          }

          let opts = {
            position: this.mapAPI.createLngLat({
              lng84: row.lng84,
              lat84: row.lat84
            }),
            width: 33,
            height: 40,
            icon: "/e6Images/point.png",
            label: row.name,
            winDetails: row.winInfo,
            offsetType: 1 //0-图标整体中心；1-图标底部中心
          };
          this.pullRect.marker = this.mapAPI.createMarker(opts);
          this.mapAPI.addOverlay(this.pullRect.marker);
          this.mapAPI.setFitView({ overlayList: [this.pullRect.marker] });
        }
      },
      exportExcel_pullRect() {
        let aIds = this.pullRect.datas.map(item => {
          return item.id;
        });
        this.$emit("exportExcel_pullRect", { aIds });
      },
      // 切换地图类型
      changeMapType(type) {
        this.mapType = type;
        this.mapAPI.changeMapType(type);
      },
      // 导航
      openLayer_navigation() {
        this.$refs.navigationLayer.showBox();
      },
      midUrl_navigation(index) {
        return `/e6Images/mid_${index + 1}.png`;
      },
      closeLayer_navigation() {
        this.$refs.navigationLayer.closeBox();
        this.$refs.resultLayer_navigation.closeBox();
        this.clearInfo_navigation();
      },
      clearInfo_navigation() {
        // 删除地图上的overlays
        if (this.navigation.start.marker) {
          this.mapAPI.removeOverlay(this.navigation.start.marker);
          this.navigation.start.marker = null;
        }
        if (this.navigation.end.marker) {
          this.mapAPI.removeOverlay(this.navigation.end.marker);
          this.navigation.end.marker = null;
        }
        this.navigation.midList.forEach(item => {
          if (item.marker) {
            this.mapAPI.removeOverlay(item.marker);
            item.marker = null;
          }
        });
        // 清除导航线（此导航line是地图api的内部方法绘制，所以调用地图方法删除）
        this.mapAPI.clearDriving();
        // if (this.navigation.line) {
        //   this.mapAPI.removeOverlay(this.navigation.line);
        //   this.navigation.line = null;
        // }
        if (this.navigation.subLine) {
          this.mapAPI.removeOverlay(this.navigation.subLine);
          this.navigation.subLine = null;
        }
        // 初始化navigation
        this.navigation = {
          start: {
            id: "",
            marker: null,
            address: ""
          },
          end: {
            id: "",
            marker: null,
            address: ""
          },
          midList: [],
          policy: "LEAST_TIME", // 导航策略
          result: [],
          odometer: 0,
          subLine: null
        };
      },
      addMid_navigation() {
        this.navigation.midList.push({
          id: "",
          marker: null
        });
        // 将滚动条滚动到最底部
        this.$nextTick(() => {
          // this.$refs.midScroll_navigation.wrap.scrollTop = this.$refs.midScroll_navigation.wrap.scrollHeight;
          this.$refs.midScroll_navigation.scrollIntoView("#mid_"+(this.navigation.midList.length-1), 500);
        });
      },
      deleteMid_navigation(index) {
        let curObj = this.getCurObjByIndex(index);
        if (curObj.marker) {
          this.mapAPI.removeOverlay(curObj.marker);
          curObj.marker = null;
        }
        this.navigation.midList.splice(index, 1);
        this.adjustMidOrder_navigation();
      },
      adjustMidOrder_navigation() {
        // 调整图标的顺序
        this.navigation.midList.forEach((item, index) => {
          if (item.marker) {
            let iconUrl = this.getIconUrl_navigation(index);
            this.mapAPI.setIcon(item.marker, { icon: iconUrl });
          }
        });
      },
      markMarker_navigation(tIndex) {
        this.mapAPI.drawOverlay({
          type: "marker",
          callback: this.markBack_navigation,
          opts: {
            icon: this.getIconUrl_navigation(tIndex),
            width: 30,
            height: 38,
            offsetType: 1,
            draggable: true
          },
          extData: {
            tIndex
          }
        });
      },
      getIconUrl_navigation(tIndex) {
        let iconName = "";
        switch (tIndex) {
          case "start":
            iconName = "start";
            break;
          case "end":
            iconName = "end";
            break;
          default:
            iconName = `mid_${tIndex + 1}`;
            break;
        }
        return `/e6Images/${iconName}.png`;
      },
      markBack_navigation(overlay, extData) {
        let curObj = this.getCurObjByIndex(extData.tIndex);
        if (curObj.marker) {
          this.mapAPI.removeOverlay(curObj.marker);
          curObj.marker = null;
        }
        curObj.marker = overlay;
        curObj.id = "";
      },
      // 根据tIndex获取当前对象（start对象，end对象，midList[n]对象）
      getCurObjByIndex(tIndex) {
        let curObj = {};
        switch (tIndex) {
          case "start":
          case "end":
            curObj = this.navigation[tIndex];
            break;
          default:
            curObj = this.navigation.midList[tIndex];
            break;
        }
        return curObj;
      },
      selectChange_navigation(tIndex, id) {
        // 删除oldMarker
        let curObj = this.getCurObjByIndex(tIndex);
        if (curObj.marker) {
          this.mapAPI.removeOverlay(curObj.marker);
          curObj.marker = null;
        }
        // 取新数据
        let oData = this.navigationDatas.find(item => {
          return item.id == id;
        });
        if (oData) {
          let lnglat = this.mapAPI.createLngLat({
            lng84: oData.lng,
            lat84: oData.lat
          });
          let opts = {
            position: lnglat,
            width: 30,
            height: 38,
            icon: this.getIconUrl_navigation(tIndex),
            offsetType: 1, //0-图标整体中心；1-图标底部中心
            draggable: true
          };
          let marker = this.mapAPI.createMarker(opts);
          // 记录marker
          curObj.marker = marker;
          this.mapAPI.addOverlay(marker);
          this.mapAPI.setFitView({ overlayList: [marker] });
        }
      },
      navigationClick() {
        if (!this.navigation.start.marker) {
          this.$message({
            message: "请选择或标注起点",
            type: "warning"
          });
          return;
        }
        if (!this.navigation.end.marker) {
          this.$message({
            message: "请选择或标注终点",
            type: "warning"
          });
          return;
        }
        let midList_temp = this.navigation.midList.filter(item => {
          return item.marker;
        });
        this.navigation.midList = midList_temp;
        // 调整图标顺序
        this.adjustMidOrder_navigation();
        // 开始导航
        let lnglat_start = this.mapAPI.getPosition(this.navigation.start.marker)
          .lnglat;
        let lnglat_end = this.mapAPI.getPosition(this.navigation.end.marker)
          .lnglat;
        let aLnglat_mid = this.navigation.midList.map(item => {
          return this.mapAPI.getPosition(item.marker).lnglat;
        });
        this.mapAPI.drivingSearch({
          opts: { policy: this.navigation.policy },
          start: lnglat_start,
          end: lnglat_end,
          waypoints: aLnglat_mid,
          callback: this.navigationBack
        });
      },
      navigationBack(status, result) {
        if (status == "error") {
          this.$message({
            message: "导航失败，请重新导航",
            type: "error"
          });
          return;
        }
        if (status == "no_data") {
          this.$message({
            message: "没有导航数据",
            type: "warning"
          });
          return;
        }
        // 处理导航结果
        if (status == "complete" && result.routes && result.routes[0]) {
          let driveRoute = result.routes[0];
          this.navigation.odometer = driveRoute.distance / 1000;
          let navResult = [];
          driveRoute.steps.forEach(item => {
            navResult.push({
              instruction: item.instruction,
              path: item.path
            });
          });
          this.navigation.result = navResult;
          // 处理起终点位置
          this.dealSEAddress_navigation();
          this.openResultLayer_navigation();
        }
      },
      dealSEAddress_navigation() {
        // start
        this.getAddreaa_navigation("start").then(res => {
          this.navigation.start.address = res;
        });
        this.getAddreaa_navigation("end").then(res => {
          this.navigation.end.address = res;
        });
      },
      getAddreaa_navigation(tIndex) {
        return new Promise((resolve, reject) => {
          let address = "";
          if (this.navigation[tIndex].id == "") {
            // 标注的
            let lnglat = this.mapAPI.getPosition(this.navigation[tIndex].marker)
              .lnglat;
            this.mapAPI.getAddress(lnglat, result => {
              if (result != null) {
                address = result.formattedAddress;
                resolve(address);
              }
            });
          } else {
            // 选择的
            let sData = this.navigationDatas.find(item => {
              return item.id == this.navigation[tIndex].id;
            });
            if (sData) {
              address = sData.name;
            }
            resolve(address);
          }
        });
      },
      saveLine_navigation() {
        let llObjArr = [];
        this.navigation.result.forEach(item => {
          item.path.forEach(item_sub => {
            llObjArr.push({
              lng02: this.mapAPI.getLng(item_sub),
              lat02: this.mapAPI.getLat(item_sub)
            });
          });
        });
        let opts = {
          odometer: this.navigation.odometer,
          bposition: this.navigation.start.address,
          eposition: this.navigation.end.address,
          llObjArr: llObjArr
        };
        this.lOpenLayer(opts);
      },
      afresh_navigation() {
        this.clearInfo_navigation();
        this.$refs.resultLayer_navigation.closeBox();
        this.$refs.navigationLayer.showBox();
      },
      closeResultLayer_navigation() {
        this.clearInfo_navigation();
        this.$refs.resultLayer_navigation.closeBox();
      },
      openResultLayer_navigation() {
        this.$refs.navigationLayer.closeBox();
        this.$refs.resultLayer_navigation.showBox();
      },
      showSubLine_navigation(index) {
        this.drawSubLine_navigation(index);
        this.mapAPI.setFitView({ overlayList: [this.navigation.subLine] });
      },
      mEnterSubLine_navigation(index) {
        this.drawSubLine_navigation(index);
      },
      mLeaveSubLine_navigation(index) {
        if(this.navigation.subLine){
          this.mapAPI.removeOverlay(this.navigation.subLine);
        }
      },
      drawSubLine_navigation(index) {
        if (this.navigation.subLine) {
          this.mapAPI.removeOverlay(this.navigation.subLine);
          this.navigation.subLine = null;
        }
        let path = this.navigation.result[index].path || [];
        this.navigation.subLine = this.mapAPI.createLine({
          path,
          strokeColor: "#409eff",
          strokeWeight: 6,
          showDir: true,
          zIndex: 500
        });
        this.mapAPI.addOverlay(this.navigation.subLine);
      }
    },
    created() {
      // 请求部门架构数据
      this.setOrganizationAct();
    },
    mounted() {
      this.$nextTick(() => {
        lazyAMapApiLoaderInstance.load().then(() => {
          this.map = new AMap.Map("amapContainer", {
            // center: new AMap.LngLat(108.942387, 34.261064), //西安
            center: new AMap.LngLat(...this.center), //宝钢
            lang: "zh-cn" //设置地图语言类型  zh-cn  en  zh-en
            // ,mapStyle: "amap://styles/darkblue" //设置地图的显示样式
            // mapStyle: "amap://styles/5442c93ddb00045a153b6a9ebef3047b"
          });
          this.map.addControl(new AMap.ToolBar());
          this.map.addControl(new AMap.Scale());
          this.mapAPI = new GaodeAPI(this.map);
          this.mapAPI.addListener_zoom(() => {
            this.mShowOrHideByLevel();
            this.aShowOrHideByLevel();
          });
          this.$emit("init");
        });
      });
    }
  };
</script>
<style lang="scss" scoped>
  @import "@/assets/css/map/theme.scss";
  .logisContainer {
    width: 100%;
    height: 100%;
    .toolsBox {
      height: 37px;
      background-color: #f6f6f6;
      border-bottom: 1px solid $--gray-color;
      box-sizing: border-box;
      .fr,
      .fl {
        display: flex;
        align-items: center;
        height: 100%;
        .toolBtn {
          white-space: nowrap;
          color: $--font-color;
          display: flex;
          align-items: center;
          justify-content: center;
          padding: 5px 10px;
          cursor: pointer;
          &:hover {
            > i {
              color: $--main-hover-color;
            }
          }
          > i {
            font-size: 16px;
            margin-right: 5px;
            color: $--main-color;
          }
        }
      }
      .fl {
        float: left;
      }
      .fr {
        float: right;
      }
      .searchBox {
        /deep/ .el-input__suffix {
          display: flex;
          align-items: center;
          color: $--main-color;
          font-size: 14px;
          cursor: pointer;
          &:hover{
            color: $--main-hover-color;
          }
        }
      }
    }
    .mapContainer {
      width: 100%;
      height: calc(100% - 37px);
      .trafficLayer {
        position: absolute;
        right: 10px;
        bottom: 2px;
        > i {
          position: absolute;
          right: -7px;
          top: -7px;
          font-size: 16px;
          color: $--font-color;
          cursor: pointer;
        }
        .trafficTip {
          /deep/ .el-card__body {
            padding: 10px;
            display: flex;
            align-items: center;
            justify-content: space-between;
          }
          label {
            font-size: 12px;
            color: $--font-color;
            margin: 0 3px;
          }
          em {
            width: 20px;
            height: 6px;
            margin: 0 1px;
            text-indent: -9999;
            &.red {
              background-color: #f23030;
              border: 1px solid #b50b0b;
            }
            &.yellow {
              background-color: #ff9f19;
              border: 1px solid #d27900;
            }
            &.green {
              background-color: #50bf39;
              border: 1px solid #368026;
            }
          }
          i {
            color: $--main-color;
            cursor: pointer;
            font-size: 14px;
            margin-left: 5px;
            &:hover {
              color: $--main-hover-color;
            }
          }
        }
      }
    }
  }
  .comDropdown {
    > ul li {
      padding: 5px 7px;
      font-size: 12px;
      cursor: pointer;
      display: flex;
      align-items: center;
      justify-content: space-between;
      &:hover {
        color: $--main-color;
        > span > span > a {
          color: $--main-color;
        }
      }
      > span {
        > i {
          font-size: 14px;
          margin-right: 5px;
          &.a_icon-yanjing,
          &.e6-icon-add_line {
            color: $--main-color;
          }
          &.a_icon-duihao {
            color: $--green-color;
          }
          &.e6-icon-minus_line {
            color: $--lightgray-font-color;
          }
        }
        > span > a {
          font-size: 12px;
          color: $--font-eleUI-color;
        }
      }
    }
  }
  .operBox {
    position: absolute;
    top: 20px;
    right: 20px;
    display: flex;
    .searchBox {
      margin-right: 7px;
      position: relative;
      top: -1px;
      > i.e6-icon-search_line {
        font-size: 14px;
      }
      /deep/ .el-input__prefix {
        display: flex;
        align-items: center;
        color: dimgrey;
        font-size: 14px !important;
      }
      /deep/ .el-input__inner {
        border: 0;
      }
    }
  }
  .logisSubPop {
    margin: 0;
    padding: 0;
    ul {
      margin: 0;
      padding: 0;
      li {
        font-size: 12px;
        list-style: none;
        margin: 0;
        cursor: pointer;
        &.active {
          color: dodgerblue;
        }
      }
    }
  }
  .cBox {
    background-color: #fff;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15);
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    > i {
      position: absolute;
      top: 10px;
      right: 10px;
      cursor: pointer;
      &:hover {
        color: cornflowerblue;
      }
    }
    ul {
      margin: 0;
      padding: 0;
      display: flex;
      align-items: center;
      justify-content: space-around;
      margin: 10px;
      li {
        list-style: none;
        display: flex;
        flex-direction: column;
        padding: 20px 25px;
        margin: 20px;
        cursor: pointer;
        i {
          font-size: 30px;
          color: #5ca0c4;
        }
        span {
          font-size: 12px;
          color: #444;
          text-align: center;
          margin-top: 5px;
        }
        &:hover {
          i {
            color: lightcoral;
          }
          background-color: #e6f2ff;
        }
      }
    }
  }
  .editLayer {
    position: absolute;
    top: 37px;
    left: 0;
    width: 520px;
    height: fit-content;
    background-color: #fff;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15);
    border-top: 3px solid #409eff;
    border-radius: 3px;
    z-index: 200;
    &.navigation {
      width: 450px;
      z-index: 190;
      .tContent .formLayer {
        /deep/ .el-form-item {
          width: 350px;
        }
        .midBox {
          max-height: 106px;
          overflow-y: auto;
          width: 100%;
          /deep/ .__view{
            min-height: auto !important;
          }
        }
        img {
          margin-left: 10px;
          cursor: pointer;
        }
        i {
          font-size: 16px;
          cursor: pointer;
          margin-left: 7px;
          color: $--main-color;
          position: absolute;
          top: 4px;
          right: -30px;
          &:hover {
            color: $--main-hover-color;
          }
        }
        /deep/ .el-select {
          width: 230px;
        }
      }
    }
    .tContent {
      background-color: #fff;
      padding: 10px 15px;
      .formLayer {
        display: flex;
        flex-wrap: wrap;
        /deep/ .el-form-item {
          width: 50%;
          margin-bottom: 15px;
          font-size: 12px;
        }
        /deep/ .el-form-item__label {
          font-size: 12px;
        }
        .wholeRow {
          width: 100%;
        }
        /deep/ .el-form-item{
          &.is-success {
            .sub-selected-val{
              border-color: $--green-color;
            }
          }
          &.is-error{
            .sub-selected-val{
              border-color: $--red-color;
            }
          }
        }
      }
      .comTxt {
        width: 40%;
      }
      .lineStyleBox {
        /deep/ .el-form-item__content {
          display: flex;
          align-items: center;
          position: relative;
        }
        .selectLineWidth {
          width: 130px;
          height: 20px;
          display: flex;
          align-items: center;
          span {
            width: 100%;
            display: inline-block;
          }
        }
      }
      /deep/ .base-select .sub-selected-value {
        .sub-selected-val {
          height: 32px;
          line-height: 32px;
          .drop {
            right: 3px;
            color: #c0c4cc;
          }
          .placeholder {
            padding-left: 10px;
          }
          .multiple-selected-item {
            height: 26px;
            line-height: 26px;
            margin-left: 15px;
            .multiple-selected-item-label {
              font-size: 13px;
              color: $--font-eleUI-color;
            }
          }
        }
        .is-active {
          border-color: $--main-color !important;
        }
      }
    }
    .horizontal {
      display: flex;
      align-items: center;
      .colorCol {
        width: 128px !important;
      }
    }
    .colorPicker {
      margin-left: 5px;
    }
    .btnBox {
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 8px 0;
      background-color: #f8f8f8;
      .btn {
        margin-right: 20px;
        &:last-child {
          margin-right: 0;
        }
      }
    }
  }
  .commonLayer {
    position: absolute;
    top: 37px;
    left: 0;
    height: fit-content;
    width: 460px;
    background-color: #fff;
    .comContent {
      padding: 15px;
      .topStat {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin: 0 0 15px;
      }
      /deep/ .el-table__row {
        cursor: pointer;
      }
    }
  }
  .mIconBox {
    position: relative;
    top: 8px;
  }
  .mIconPop {
    display: flex;
    flex-wrap: wrap;
    width: 160px;
    img {
      margin: 6px;
      cursor: pointer;
    }
  }
  .fl {
    float: left;
  }
  .fr {
    float: right;
  }
  .cPointer {
    cursor: pointer;
    outline: none;
    padding: 0 2px;
    position: relative;
    top: 2px;
  }
  .lineWidthPop {
    height: 464px;
    ul {
      li {
        list-style: none;
        width: 158px;
        margin: 15px;
        cursor: pointer;
      }
    }
  }
  .intelligent {
    position: relative;
    .content {
      position: relative;
      > i {
        position: absolute;
        right: -6px;
        top: -13px;
        font-size: 14px;
        cursor: pointer;
      }
    }
    .eBox {
      margin: 3px 0;
      padding-bottom: 3px;
      border-bottom: 1px solid $--grey-border-color;
      &:last-child {
        border-bottom: 0;
      }
      .top {
        display: flex;
        align-items: center;
        justify-content: space-between;
        h5 {
          margin: 5px 7px;
        }
        i {
          color: $--main-color;
          margin-right: 5px;
          cursor: pointer;
          &:last-child {
            margin-right: 0;
          }
        }
      }
      .content {
        display: flex;
        align-items: center;
        flex-wrap: wrap;
        li {
          font-size: 12px;
          padding: 4px 7px;
          width: 72px;
          text-overflow: ellipsis;
          white-space: nowrap;
          overflow: hidden;
          box-sizing: border-box;
          color: $--main-color;
          cursor: pointer;
        }
      }
    }
  }
  .intelligentLayer {
    width: 360px;
    .intelligentMore {
      height: 460px;
      overflow-y: auto;
      .oneOption {
        position: relative;
        padding: 5px;
        &:nth-child(2n) {
          background-color: $--grayBg-color;
        }
        h3 {
          position: absolute;
          left: 16px;
          top: 0;
          color: $--num-color;
          font-style: italic;
          font-size: 16px;
        }
        p {
          padding-left: 40px;
          margin: 5px 0;
          color: $--font-color;
          font-size: 12px;
          a {
            color: $--main-color;
            &:hover {
              color: $--main-hover-color;
            }
          }
        }
      }
    }
  }
  .navigationResultBox {
    .summary {
      padding: 15px;
      display: flex;
      align-items: center;
      justify-content: space-between;

      .point {
        text-align: center;
        width: 27%;
        p {
          width: 100%;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
          margin: 10px 0 0;
        }
      }
      .odo {
        padding: 0 15px;
        flex-grow: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        .text {
          text-align: center;
          color: #7c8aa0;
          font-size: 12px;
        }
      }
    }
    .subLine {
      max-height: 300px;
      overflow-y: auto;
      width: 100%;
      .oneSub {
        padding: 5px;
        cursor: pointer;
        &:nth-child(2n + 1) {
          background-color: $--grayBg-color;
        }
        display: flex;
        align-items: center;
        h3 {
          color: $--num-color;
          font-style: italic;
          font-size: 16px;
          margin: 0 10px;
          width: 30px;
          text-align: center;
        }
        p {
          margin: 5px 0;
          color: $--font-color;
          font-size: 12px;
          color: $--main-color;
          &:hover {
            color: $--main-hover-color;
          }
        }
      }
    }
    /deep/ .el-scrollbar__wrap {
      max-height: 300px;
      padding-bottom: 17px;
      .el-scrollbar__view {
        width: 100%;
      }
    }
  }
</style>
<style lang="scss">
  .logisSubPop.el-popover {
    min-width: auto !important;
  }
  .comDropdown {
    min-width: initial !important;
  }
</style>
