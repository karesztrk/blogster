<div class="editor btn-toolbar" data-role="editor-toolbar" data-target="#editor">
  <div class="btn-group">
    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Font"><i class="fa fa-font"></i><b class="caret"></b></a>
      <ul class="dropdown-menu">
      </ul>
    </div>
  <div class="btn-group">
    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Font Size"><i class="fa fa-text-width"></i>&nbsp;<b class="caret"></b></a>
      <ul class="dropdown-menu">
      <li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
      <li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
      <li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
      </ul>
  </div>
  <div class="btn-group">
    <a class="btn btn-default" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="fa fa-bold"></i></a>
    <a class="btn btn-default" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a>
    <!-- Unsupported icon <a class="btn btn-default" data-edit="strikethrough" title="Strikethrough"><i class="icon-strikethrough"></i></a>  -->
    <a class="btn btn-default" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i class="fa fa-underline"></i></a>
  </div>
  <div class="btn-group">
    <a class="btn btn-default" data-edit="insertunorderedlist" title="Bullet list"><i class="fa fa-list-ul"></i></a>
    <a class="btn btn-default" data-edit="insertorderedlist" title="Number list"><i class="fa fa-list-ol"></i></a>
    <a class="btn btn-default" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i class="fa fa-dedent"></i></a>
    <a class="btn btn-default" data-edit="indent" title="Indent (Tab)"><i class="fa fa-indent"></i></a>
  </div>
  <div class="btn-group">
    <a class="btn btn-default" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i class="fa fa-align-left"></i></a>
    <a class="btn btn-default" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i class="fa fa-align-center"></i></a>
    <a class="btn btn-default" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i class="fa fa-align-right"></i></a>
    <a class="btn btn-default" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i class="fa fa-align-justify"></i></a>
  </div>
  <div class="btn-group">
	<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Hyperlink"><i class="fa fa-link"></i></a>
  	<div class="dropdown-menu input-append">
   		<input class="span2" placeholder="URL" type="text" data-edit="createLink"/>
   		<button class="btn" type="button">Add</button>
    </div>
    <a class="btn btn-default" data-edit="unlink" title="Remove Hyperlink"><i class="fa fa-unlink"></i></a>

  </div>
  
<div class="btn-group">
<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Add video"><i class="fa fa-youtube-play"></i></a>
 	<div class="dropdown-menu input-append">
  		<input class="span2" placeholder="Video URL" type="text" data-edit="createEmbeddedVideo"/>
  		<button class="btn" type="button">Add</button>
   </div>

</div>
  <!-- Not supported yet by us
  <div class="btn-group">
    <a class="btn btn-default" title="Insert picture (or just drag & drop)" id="pictureBtn"><i class="icon-picture"></i></a>
    <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
  </div>
   -->
   
  <div class="btn-group">
    <a class="btn btn-default" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="fa fa-undo"></i></a>
    <a class="btn btn-default" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="fa fa-repeat"></i></a>
  </div>
  <input type="text" data-edit="inserttext" id="voiceBtn" x-webkit-speech="">
</div>